// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common.simulation;

import com.revrobotics.sim.SparkLimitSwitchSim;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.sim.SparkRelativeEncoderSim;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4048.common.Constants;

import static edu.wpi.first.units.Units.*;

/**
 * A class to encapsulate the behavior of a simulated single jointed arm.
 * Wraps around the motor and the physical model to simulate the behavior.
 * Send information to ShuffleBoard with physics information.
 * Does not interfere with production behavior.
 */
public class ArmSimulator {
    // Gearbox represents a gearbox (1:1 conversion rate) with 1 motor connected
    private final DCMotor gearbox = DCMotor.getNEO(1);
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
    private final SingleJointedArmSim armSim =
            new SingleJointedArmSim(
                    gearbox,
                    Constants.armGearing,
                    Constants.armInertia,
                    Constants.armLength,
                    Constants.armMinAngle,
                    Constants.armMaxAngle,
                    true,
                    0);
    // The mechanism simulation used for visualization
    private final Mechanism2d mech2d = new Mechanism2d(3, 5);
    private final MechanismRoot2d mech2dRoot = mech2d.getRoot("Arm Root", 1, 1);
    private final MechanismLigament2d armMech2d =
            mech2dRoot.append(
                    new MechanismLigament2d("Arm", 1, -90));

    /**
     * Constructor.
     */
    public ArmSimulator(SparkMax motor) {
        this.motor = motor;
        motorSim = new SparkMaxSim(motor, gearbox);
        encoderSim = motorSim.getRelativeEncoderSim();
        forwardSwitchSim = motorSim.getForwardLimitSwitchSim();
        reverseSwitchSim = motorSim.getReverseLimitSwitchSim();

        encoderSim.setPositionConversionFactor(1.0);
        encoderSim.setPosition(0.0);
        encoderSim.setInverted(false);

        // Publish Mechanism2d to SmartDashboard
        // To view the Elevator visualization, select Network Tables -> SmartDashboard -> Elevator Sim
        SmartDashboard.putData("Arm Sim", mech2d);
    }

    /**
     * Advance the simulation.
     */
    public void simulationPeriodic() {
        // In this method, we update our simulation of what our elevator is doing
        // First, we set our "inputs" (voltages)
        double motorOut = motorSim.getAppliedOutput() * 12.0;// * RoboRioSim.getVInVoltage();
        armSim.setInput(motorOut);
        // Next, we update it. The standard loop time is 20ms.
        armSim.update(0.020);

        // Finally, we set our simulated encoder's readings and simulated battery voltage
        double velocityRadsPerSecond = armSim.getVelocityRadPerSec();
        double rpm = convertAngleToRotations(Radians.of(velocityRadsPerSecond)).per(Second).in(RPM);
        motorSim.iterate(
                rpm,
                12,//RoboRioSim.getVInVoltage(),
                0.020);
        // SimBattery estimates loaded battery voltages
        RoboRioSim.setVInVoltage(
                BatterySim.calculateDefaultBatteryLoadedVoltage(armSim.getCurrentDrawAmps()));

        // Update elevator visualization with position
        double positionRadians = armSim.getAngleRads();
        armMech2d.setAngle(Radians.of(positionRadians).in(Degrees));

        forwardSwitchSim.setPressed(armSim.hasHitUpperLimit());
        reverseSwitchSim.setPressed(armSim.hasHitLowerLimit());

        SmartDashboard.putNumber("Arm Motor out voltage", motorOut);
        SmartDashboard.putNumber("Arm Velocity rads/s", velocityRadsPerSecond);
        SmartDashboard.putNumber("Arm RPM", rpm);
        SmartDashboard.putNumber("Arm actual position", armSim.getAngleRads());
        SmartDashboard.putNumber("Arm Mechanism angle", armMech2d.getAngle());
        SmartDashboard.putBoolean("Arm Forward switch", forwardSwitchSim.getPressed());
        SmartDashboard.putBoolean("Arm Reverse switch", reverseSwitchSim.getPressed());
        SmartDashboard.putNumber("Arm Encoder", encoderSim.getPosition());
    }

    /**
     * Convert {@link Angle} into RPM
     *
     * @param angle Rotation angle, in Radians.
     * @return {@link Angle} equivalent to rotations of the motor.
     */
    private Angle convertAngleToRotations(Angle angle) {
        return Rotations.of(angle.in(Radians) * Constants.armGearing);
    }

    public void close() {
        motor.close();
        mech2d.close();
    }
}
