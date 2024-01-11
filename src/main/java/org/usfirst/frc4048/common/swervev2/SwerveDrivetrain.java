
//EXAMPLE FOR 2023 ROBOT
/*
package org.usfirst.frc4048.common.swervev2;


import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.swervev2.components.EncodedSwerveSparkMax;
import frc.robot.swervev2.type.SparkMaxSwerveModule;


 private static final double MODULE_MAX_ANGULAR_ACCELERATION = 2 * Math.PI; // radians per second squared
    
     private final SparkMaxSwerveModule frontLeft;
    private final SparkMaxSwerveModule frontRight;
    private final SparkMaxSwerveModule backLeft;
    private final SparkMaxSwerveModule backRight;

    private final Translation2d frontLeftLocation = new Translation2d(Constants.ROBOT_LENGTH/2, Constants.ROBOT_WIDTH/2);
    private final Translation2d frontRightLocation = new Translation2d(Constants.ROBOT_LENGTH/2, -Constants.ROBOT_WIDTH/2);
    private final Translation2d backLeftLocation = new Translation2d(-Constants.ROBOT_LENGTH/2, Constants.ROBOT_WIDTH/2);
    private final Translation2d backRightLocation = new Translation2d(-Constants.ROBOT_LENGTH/2, -Constants.ROBOT_WIDTH/2);
    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(frontLeftLocation,frontRightLocation,backLeftLocation,backRightLocation);

    private final double gyroOffset = 180;
    private final AHRS navxGyro;
    

    private double getGyro() {
        return (navxGyro.getAngle() % 360) * -1;
    }

    public SwerveDrivetrain(SwerveIdConfig frontLeftConfig, SwerveIdConfig frontRightConfig, SwerveIdConfig backLeftConfig, SwerveIdConfig backRightConfig, KinematicsConversionConfig conversionConfig) {
        navxGyro = new AHRS();
        EncodedSwerveSparkMax encodedSwerveSparkMaxFL = new EncodedSwerveMotorBuilder(frontLeftConfig, conversionConfig, true).build();
        EncodedSwerveSparkMax encodedSwerveSparkMaxFR = new EncodedSwerveMotorBuilder(frontRightConfig, conversionConfig, false).build();
        EncodedSwerveSparkMax encodedSwerveSparkMaxBL = new EncodedSwerveMotorBuilder(backLeftConfig, conversionConfig, true).build();
        EncodedSwerveSparkMax encodedSwerveSparkMaxBR = new EncodedSwerveMotorBuilder(backRightConfig, conversionConfig, false).build();

        TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(Constants.MAX_ANGULAR_SPEED * 4, MODULE_MAX_ANGULAR_ACCELERATION * 10);
        PID drivePid = PID.of(Constants.DRIVE_PID_P,Constants.DRIVE_PID_I,Constants.DRIVE_PID_D);
        PID steerPid = PID.of(Constants.STEER_PID_P,Constants.STEER_PID_I,Constants.STEER_PID_D);
        Gain driveGain = Gain.of(Constants.DRIVE_PID_FF_V,Constants.DRIVE_PID_FF_S);
        Gain steerGain = Gain.of(Constants.STEER_PID_FF_V,Constants.STEER_PID_FF_S);
        frontLeft = new SparkMaxSwerveModule(encodedSwerveSparkMaxFL, drivePid,steerPid, driveGain,steerGain,constraints);
        frontRight = new SparkMaxSwerveModule(encodedSwerveSparkMaxFR, drivePid,steerPid, driveGain,steerGain,constraints);
        backLeft = new SparkMaxSwerveModule(encodedSwerveSparkMaxBL, drivePid,steerPid, driveGain,steerGain,constraints);
        backRight = new SparkMaxSwerveModule(encodedSwerveSparkMaxBR, drivePid,steerPid, driveGain,steerGain,constraints);

        navxGyro.setAngleAdjustment(gyroOffset);
    }
    
    public float getRoll() {
        return navxGyro.getRoll();
    }


    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, new Rotation2d(Math.toRadians(getGyro())))
                        : new ChassisSpeeds(xSpeed, ySpeed, rot));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.MAX_VELOCITY);
        setModuleStates(swerveModuleStates);
    }

    private void setModuleStates(SwerveModuleState[] desiredStates) {
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }

    public void stopMotor() {
        frontLeft.getSwerveMotor().getSteerMotor().set(0.0);
        frontLeft.getSwerveMotor().getDriveMotor().set(0.0);
        frontRight.getSwerveMotor().getSteerMotor().set(0.0);
        frontRight.getSwerveMotor().getDriveMotor().set(0.0);
        backLeft.getSwerveMotor().getSteerMotor().set(0.0);
        backLeft.getSwerveMotor().getDriveMotor().set(0.0);
        backRight.getSwerveMotor().getSteerMotor().set(0.0);
        backRight.getSwerveMotor().getDriveMotor().set(0.0);
    }

    public void zeroRelativeEncoders(){
        frontLeft.getSwerveMotor().resetRelEnc();
        frontRight.getSwerveMotor().resetRelEnc();
        backLeft.getSwerveMotor().resetRelEnc();
        backRight.getSwerveMotor().resetRelEnc();
    }

    public SparkMaxSwerveModule getFrontLeft() {
        return frontLeft;
    }

    public SparkMaxSwerveModule getFrontRight() {
        return frontRight;
    }

    public SparkMaxSwerveModule getBackLeft() {
        return backLeft;
    }

    public SparkMaxSwerveModule getBackRight() {
        return backRight;
    }
*/
