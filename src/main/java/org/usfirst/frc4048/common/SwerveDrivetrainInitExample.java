package org.usfirst.frc4048.common;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.usfirst.frc4048.common.apriltags.ApriltagInputs;
import org.usfirst.frc4048.common.apriltags.MockApriltag;
import org.usfirst.frc4048.common.apriltags.NtApriltag;
import org.usfirst.frc4048.common.gyro.GyroIO;
import org.usfirst.frc4048.common.gyro.MockGyroIO;
import org.usfirst.frc4048.common.gyro.RealGyroIO;
import org.usfirst.frc4048.common.gyro.ThreadedGyro;
import org.usfirst.frc4048.common.swervev2.SwerveIdConfig;
import org.usfirst.frc4048.common.swervev2.SwervePidConfig;
import org.usfirst.frc4048.common.swervev3.KinematicsConversionConfig;
import org.usfirst.frc4048.common.swervev3.SwerveDrivetrain;
import org.usfirst.frc4048.common.swervev3.SwerveModule;
import org.usfirst.frc4048.common.swervev3.io.abs.CANCoderAbsIO;
import org.usfirst.frc4048.common.swervev3.io.abs.MockAbsIO;
import org.usfirst.frc4048.common.swervev3.io.drive.MockDriveMotorIO;
import org.usfirst.frc4048.common.swervev3.io.drive.SparkMaxDriveMotorIO;
import org.usfirst.frc4048.common.swervev3.io.steer.MockSteerMotorIO;
import org.usfirst.frc4048.common.swervev3.io.steer.SparkMaxSteerMotorIO;
import org.usfirst.frc4048.common.util.Gain;
import org.usfirst.frc4048.common.util.LoggableIO;
import org.usfirst.frc4048.common.util.PID;

public class SwerveDrivetrainInitExample {

    private void setupDriveTrain() {
        SwerveIdConfig frontLeftIdConf = new SwerveIdConfig(Constants.DRIVE_FRONT_LEFT_D, Constants.DRIVE_FRONT_LEFT_S, Constants.DRIVE_CANCODER_FRONT_LEFT);
        SwerveIdConfig frontRightIdConf = new SwerveIdConfig(Constants.DRIVE_FRONT_RIGHT_D, Constants.DRIVE_FRONT_RIGHT_S, Constants.DRIVE_CANCODER_FRONT_RIGHT);
        SwerveIdConfig backLeftIdConf = new SwerveIdConfig(Constants.DRIVE_BACK_LEFT_D, Constants.DRIVE_BACK_LEFT_S, Constants.DRIVE_CANCODER_BACK_LEFT);
        SwerveIdConfig backRightIdConf = new SwerveIdConfig(Constants.DRIVE_BACK_RIGHT_D, Constants.DRIVE_BACK_RIGHT_S, Constants.DRIVE_CANCODER_BACK_RIGHT);

        TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(Constants.MAX_ANGULAR_SPEED * 150, 2 * Math.PI * 150);
        PID drivePid = PID.of(Constants.DRIVE_PID_P, Constants.DRIVE_PID_I, Constants.DRIVE_PID_D);
        PID steerPid = PID.of(Constants.STEER_PID_P, Constants.STEER_PID_I, Constants.STEER_PID_D);
        Gain driveGain = Gain.of(Constants.DRIVE_PID_FF_V, Constants.DRIVE_PID_FF_S);
        Gain steerGain = Gain.of(Constants.STEER_PID_FF_V, Constants.STEER_PID_FF_S);

        KinematicsConversionConfig kinematicsConversionConfig = new KinematicsConversionConfig(Constants.WHEEL_RADIUS, Constants.SWERVE_MODULE_PROFILE);
        SwervePidConfig pidConfig = new SwervePidConfig(drivePid, steerPid, driveGain, steerGain, constraints);
        SwerveModule frontLeft;
        SwerveModule frontRight;
        SwerveModule backLeft;
        SwerveModule backRight;

        GyroIO gyroIO;
        LoggableIO<ApriltagInputs> apriltagIO;

        if (ExampleAdvantageScopeRobot.isReal()){
            frontLeft = SwerveModule.createModule(frontLeftIdConf, kinematicsConversionConfig, pidConfig, "frontLeft");
            frontRight = SwerveModule.createModule(frontRightIdConf, kinematicsConversionConfig, pidConfig, "frontRight");
            backLeft = SwerveModule.createModule(backLeftIdConf, kinematicsConversionConfig, pidConfig, "backLeft");
            backRight = SwerveModule.createModule(backRightIdConf, kinematicsConversionConfig, pidConfig, "backRight");

            ThreadedGyro threadedGyro = new ThreadedGyro(new AHRS());
            threadedGyro.start();
            gyroIO = new RealGyroIO(threadedGyro);
            apriltagIO = new NtApriltag();
        }else{
            frontLeft = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "frontLeft");
            frontRight = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "frontRight");
            backLeft = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "backLeft");
            backRight = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "backRight");
            gyroIO = new MockGyroIO();
            apriltagIO = new MockApriltag();
        }
        SwerveDrivetrain drivetrain = new SwerveDrivetrain(frontLeft, frontRight, backLeft, backRight, gyroIO, apriltagIO);
    }

}
