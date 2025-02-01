// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common.usage.example.robot.subsystems.elevator;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.NeoPidMotor;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Rotations;

public class ElevatorSubsystem extends SubsystemBase {
    private final NeoPidMotor pidMotor = new NeoPidMotor(Constants.kElevatorMotorPort);
    private final SparkMax motor = pidMotor.getNeoMotor();
    private final ElevatorSimulator elevatorSimulator;

    /**
     * Subsystem constructor.
     */
    public ElevatorSubsystem() {
        elevatorSimulator = new ElevatorSimulator(motor);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("PID set position", pidMotor.getPidPosition());
        SmartDashboard.putNumber("PID actual position", motor.getEncoder().getPosition());
    }

    @Override
    public void simulationPeriodic() {
        elevatorSimulator.simulationPeriodic();
    }

    /**
     * Run control loop to reach and maintain goal.
     *
     * @param goal the position to maintain in meters
     */
    public void setPidGoal(double goal) {
        pidMotor.setPidPos(convertDistanceToRotations(Meters.of(goal)).in(Rotations));
    }

    /**
     * Run the motor manually
     */
    public void setMotorSpeed(double power) {
        SmartDashboard.putNumber("Elevator", power);
        motor.setVoltage(power * 12);
    }

    /**
     * Stop the control loop and motor output.
     */
    public void stop() {
        SmartDashboard.putNumber("Elevator", 0);
        motor.set(0.0);
    }

    public void close() {
        // m_encoder.close();
        motor.close();
        elevatorSimulator.close();
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
}
