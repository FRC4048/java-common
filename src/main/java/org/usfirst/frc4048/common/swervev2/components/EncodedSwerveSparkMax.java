package org.usfirst.frc4048.common.swervev2.components;

import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.revrobotics.CANSparkMax;

public class EncodedSwerveSparkMax extends GenericEncodedSwerve {
    public EncodedSwerveSparkMax(CANSparkMax driveMotor, CANSparkMax steerMotor, WPI_CANCoder absEncoder, double driveVelFactor, double drivePosFactor, double steerPosFactor) {
        super(driveMotor, steerMotor, absEncoder, driveMotor.getEncoder(), steerMotor.getEncoder(), driveVelFactor, drivePosFactor, steerPosFactor);
    }
}
