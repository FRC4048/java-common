// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common.usage.example.robot.subsystems.elevator;

import com.revrobotics.sim.SparkLimitSwitchSim;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.sim.SparkRelativeEncoderSim;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import static edu.wpi.first.units.Units.*;

/**
 * A class to encapsulate the behavior of a simulated elevator.
 * Wraps around the motor and the physical model to simulate the behavior.
 * Send information to ShuffleBoard with physics information.
 * Does not interfere with production behavior.
 */
public class ElevatorSimulator {
    // Gearbox represents a gearbox (1:1 conversion rate) with 1 or motors connected
    private final DCMotor elevatorGearbox = DCMotor.getNEO(1);
    // The motor (that sits underneath the motor simulator)
    // In case of follower/leader, this should be the leader
    private final SparkMax motor;

    // The simulated motor controller wrapping the actual motor
    private final SparkMaxSim motorSim;
    // The encoder simulator from the simulated motor
    private final SparkRelativeEncoderSim encoderSim;
    // The forward switch simulator
    private final SparkLimitSwitchSim forwardSwitchSim;
    // The reverse switch simulator
    private final SparkLimitSwitchSim reverseSwitchSim;


    // Elevator physical model, simulating movement based on physics, motor load and gravity
    private final ElevatorSim m_elevatorSim =
            new ElevatorSim(
                    elevatorGearbox,
                    Constants.kElevatorGearing,
                    Constants.kCarriageMass,
                    Constants.kElevatorDrumRadius,
                    Constants.kMinElevatorHeightMeters,
                    Constants.kMaxElevatorHeightMeters,
                    true,
                    0);
    // The mechanism simulation used for visualization
    private final Mechanism2d mech2d = new Mechanism2d(3, 5);
    private final MechanismRoot2d mech2dRoot = mech2d.getRoot("Elevator Root", 1, 0);
    private final MechanismLigament2d elevatorMech2d =
            mech2dRoot.append(
                    new MechanismLigament2d("Elevator", m_elevatorSim.getPositionMeters(), 90));

    /**
     * Constructor.
     */
    public ElevatorSimulator(SparkMax motor) {
        this.motor = motor;
        motorSim = new SparkMaxSim(motor, elevatorGearbox);
        encoderSim = motorSim.getRelativeEncoderSim();
        forwardSwitchSim = motorSim.getForwardLimitSwitchSim();
        reverseSwitchSim = motorSim.getReverseLimitSwitchSim();

        encoderSim.setPositionConversionFactor(1.0);
        encoderSim.setPosition(0.0);
        encoderSim.setInverted(false);

        // Publish Mechanism2d to SmartDashboard
        // To view the Elevator visualization, select Network Tables -> SmartDashboard -> Elevator Sim
        SmartDashboard.putData("Elevator Sim", mech2d);
    }

    /**
     * Advance the simulation.
     */
    public void simulationPeriodic() {
        // In this method, we update our simulation of what our elevator is doing
        // First, we set our "inputs" (voltages)
        double motorOut = motorSim.getAppliedOutput() * 12.0;// * RoboRioSim.getVInVoltage();
        m_elevatorSim.setInput(motorOut);
        // Next, we update it. The standard loop time is 20ms.
        m_elevatorSim.update(0.020);

        // Finally, we set our simulated encoder's readings and simulated battery voltage
        double velocityMetersPerSecond = m_elevatorSim.getVelocityMetersPerSecond();
        double rpm = convertDistanceToRotations(Meters.of(velocityMetersPerSecond)).per(Second).in(RPM);
        motorSim.iterate(
                rpm,
                12,//RoboRioSim.getVInVoltage(),
                0.020);
        // SimBattery estimates loaded battery voltages
        RoboRioSim.setVInVoltage(
                BatterySim.calculateDefaultBatteryLoadedVoltage(m_elevatorSim.getCurrentDrawAmps()));

        // Update elevator visualization with position
        double positionMeters = m_elevatorSim.getPositionMeters();
        elevatorMech2d.setLength(positionMeters);

        forwardSwitchSim.setPressed(MathUtil.isNear(Constants.kMaxElevatorHeightMeters, positionMeters, 0.1));
        reverseSwitchSim.setPressed(MathUtil.isNear(Constants.kMinElevatorHeightMeters, positionMeters, 0.1));

        SmartDashboard.putNumber("Motor out voltage", motorOut);
        SmartDashboard.putNumber("Velocity mps", velocityMetersPerSecond);
        SmartDashboard.putNumber("RPM", rpm);
        SmartDashboard.putNumber("Elevator actual position", m_elevatorSim.getPositionMeters());
        SmartDashboard.putNumber("Mechanism length", elevatorMech2d.getLength());
        SmartDashboard.putBoolean("Forward switch", forwardSwitchSim.getPressed());
        SmartDashboard.putBoolean("Reverse switch", reverseSwitchSim.getPressed());
    }

    /**
     * Convert {@link Distance} into {@link Angle}
     *
     * @param distance Distance, usually Meters.
     * @return {@link Angle} equivalent to rotations of the motor.
     */
    private Angle convertDistanceToRotations(Distance distance) {
        return Rotations.of(distance.in(Meters) /
                (Constants.kElevatorDrumRadius * 2 * Math.PI) *
                Constants.kElevatorGearing);
    }

    public void close() {
        // m_encoder.close();
        motor.close();
        mech2d.close();
    }
}
