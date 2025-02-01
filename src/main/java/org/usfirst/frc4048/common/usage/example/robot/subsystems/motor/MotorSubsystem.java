package org.usfirst.frc4048.common.usage.example.robot.subsystems.motor;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MotorSubsystem extends SubsystemBase {
    private final SparkMax motor = new SparkMax(Constants.kMotorPort, SparkLowLevel.MotorType.kBrushless);
    private RelativeEncoder encoder = motor.getEncoder();
    private final MotorSimulator motorSimulator;

    public MotorSubsystem() {
        motorSimulator = new MotorSimulator(motor);
    }

    /**
     * Run the motor manually
     */
    public void setMotorSpeed(double power) {
        SmartDashboard.putNumber("Motor power", power);
        motor.setVoltage(power * 12);
    }

    public void stop() {
        setMotorSpeed(0.0);
    }

    public void close() {
        motor.close();
    }

    @Override
    public void simulationPeriodic() {
        motorSimulator.simulationPeriodic();
    }

    @Override
    public void periodic() {
        encoder = motor.getEncoder();
        SmartDashboard.putNumber("Motor encoder position", encoder.getPosition());
        SmartDashboard.putNumber("Motor encoder velocity", encoder.getVelocity());
    }
}
