package org.usfirst.frc4048.common.swervev2.components;

import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class GenericEncodedSwerve implements SwerveMotor, SwerveMotorEncoder {
    private final MotorController driveMotor;
    private final MotorController steerMotor;

    private final RelativeEncoder driveEncoder;
    private final RelativeEncoder steerEncoder;
    private double steerOffset = 0;
    private final WPI_CANCoder absEncoder;

    public GenericEncodedSwerve(MotorController driveMotor, MotorController steerMotor, WPI_CANCoder absEncoder, RelativeEncoder driveEncoder, RelativeEncoder steerEncoder,
                                double driveVelFactor, double drivePosFactor, double steerPosFactor) {
        this.driveMotor = driveMotor;
        this.steerMotor = steerMotor;
        this.absEncoder = absEncoder;
        this.driveEncoder = driveEncoder;
        this.steerEncoder = steerEncoder;
        configureEncoders(driveVelFactor,drivePosFactor, steerPosFactor);
    }
    public void configureEncoders(double driveVelFactor, double drivePosFactor, double steerPosFactor){
        resetRelEnc();
        driveEncoder.setVelocityConversionFactor(driveVelFactor);
        driveEncoder.setPositionConversionFactor(drivePosFactor);
        steerEncoder.setPositionConversionFactor(steerPosFactor);
    }

    @Override
    public MotorController getSteerMotor() {
        return steerMotor;
    }

    @Override
    public MotorController getDriveMotor() {
        return driveMotor;
    }

    @Override
    public double getSteerEncPosition() {
        double value = steerEncoder.getPosition() - getSteerOffset();
        value %= 2 * Math.PI;
        if (value < 0) {
            value += 2 * Math.PI;
        }
        return (value);
    }

    @Override
    public double getDriveEncPosition() {
        return driveEncoder.getPosition();
    }

    @Override
    public void resetRelEnc() {
        steerEncoder.setPosition(0);
        driveEncoder.setPosition(0);
    }

    @Override
    public double getDriveEncVel() {
        return driveEncoder.getVelocity();
    }

    @Override
    public double getSteerOffset() {
        return steerOffset;
    }

    @Override
    public void setSteerOffset(double zeroAbs) {
        steerEncoder.setPosition(0);
        steerOffset = Math.toRadians(zeroAbs - absEncoder.getAbsolutePosition());
        steerOffset %= 2 * Math.PI;
        if (steerOffset < 0) {
            steerOffset += 2 * Math.PI;
        }
    }
    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(getDriveEncPosition(),new Rotation2d(getSteerEncPosition()));
    }
}
