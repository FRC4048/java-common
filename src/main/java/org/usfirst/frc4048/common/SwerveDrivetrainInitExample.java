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
import org.usfirst.frc4048.common.swervev3.io.SwerveModule;
import org.usfirst.frc4048.common.swervev3.io.abs.MockAbsIO;
import org.usfirst.frc4048.common.swervev3.io.drive.MockDriveMotorIO;
import org.usfirst.frc4048.common.swervev3.io.steer.MockSteerMotorIO;
import org.usfirst.frc4048.common.util.Gain;
import org.usfirst.frc4048.common.util.LoggableIO;
import org.usfirst.frc4048.common.util.ModulePosition;
import org.usfirst.frc4048.common.util.PID;

public class SwerveDrivetrainInitExample {

//    private void setupDriveTrain() {
//        SwerveIdConfig frontLeftIdConf = new SwerveIdConfig(Constants.DRIVE_FRONT_LEFT_D, Constants.DRIVE_FRONT_LEFT_S, Constants.DRIVE_CANCODER_FRONT_LEFT);
//        SwerveIdConfig frontRightIdConf = new SwerveIdConfig(Constants.DRIVE_FRONT_RIGHT_D, Constants.DRIVE_FRONT_RIGHT_S, Constants.DRIVE_CANCODER_FRONT_RIGHT);
//        SwerveIdConfig backLeftIdConf = new SwerveIdConfig(Constants.DRIVE_BACK_LEFT_D, Constants.DRIVE_BACK_LEFT_S, Constants.DRIVE_CANCODER_BACK_LEFT);
//        SwerveIdConfig backRightIdConf = new SwerveIdConfig(Constants.DRIVE_BACK_RIGHT_D, Constants.DRIVE_BACK_RIGHT_S, Constants.DRIVE_CANCODER_BACK_RIGHT);
//
//        TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(Constants.MAX_ANGULAR_SPEED * 150, 2 * Math.PI * 150);
//        PID drivePid = PID.of(Constants.DRIVE_PID_P, Constants.DRIVE_PID_I, Constants.DRIVE_PID_D);
//        PID steerPid = PID.of(Constants.STEER_PID_P, Constants.STEER_PID_I, Constants.STEER_PID_D);
//        Gain driveGain = Gain.of(Constants.DRIVE_PID_FF_V, Constants.DRIVE_PID_FF_S);
//        Gain steerGain = Gain.of(Constants.STEER_PID_FF_V, Constants.STEER_PID_FF_S);
//
//        KinematicsConversionConfig kConfig = new KinematicsConversionConfig(Constants.WHEEL_RADIUS, Constants.SWERVE_MODULE_PROFILE);
//        SwervePidConfig pidConfig = new SwervePidConfig(drivePid, steerPid, driveGain, steerGain, constraints);
//
//        SwerveModule frontLeft;
//        SwerveModule frontRight;
//        SwerveModule backLeft;
//        SwerveModule backRight;
//
//        GyroIO gyroIO;
//        LoggableIO<ApriltagInputs> apriltagI
//        O;
//        if (Robot.isReal()) {
//            frontLeft = SwerveModule.createModule(frontLeftIdConf, kConfig, pidConfig, ModulePosition.FRONT_LEFT);
//            frontRight = SwerveModule.createModule(frontRightIdConf, kConfig, pidConfig, ModulePosition.FRONT_RIGHT);
//            backLeft = SwerveModule.createModule(backLeftIdConf, kConfig, pidConfig, ModulePosition.BACK_LEFT);
//            backRight = SwerveModule.createModule(backRightIdConf, kConfig, pidConfig, ModulePosition.BACK_RIGHT);
//
//            ThreadedGyro threadedGyro = new ThreadedGyro(new AHRS());
//            threadedGyro.start();
//            gyroIO = new RealGyroIO(threadedGyro);
//            apriltagIO = new NtApriltag();
//        } else {
//            frontLeft = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "frontLeft");
//            frontRight = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "frontRight");
//            backLeft = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "backLeft");
//            backRight = new SwerveModule(new MockDriveMotorIO(), new MockSteerMotorIO(), new MockAbsIO(), pidConfig, "backRight");
//            gyroIO = new MockGyroIO();
//            apriltagIO = new MockApriltag();
//        }
//        SwerveDrivetrain drivetrain = new SwerveDrivetrain(frontLeft, frontRight, backLeft, backRight, gyroIO, apriltagIO);
//    }

}
