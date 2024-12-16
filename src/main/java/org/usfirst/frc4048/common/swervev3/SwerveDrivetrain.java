package org.usfirst.frc4048.common.swervev3;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.usfirst.frc4048.common.Constants;
import org.usfirst.frc4048.common.apriltags.ApriltagInputs;
import org.usfirst.frc4048.common.gyro.GyroIO;
import org.usfirst.frc4048.common.gyro.GyroInputs;
import org.usfirst.frc4048.common.swervev3.bags.OdometryMeasurement;
import org.usfirst.frc4048.common.swervev3.estimation.PoseEstimator;
import org.usfirst.frc4048.common.swervev3.io.SwerveModule;
import org.usfirst.frc4048.common.util.DriveMode;
import org.usfirst.frc4048.common.util.LoggableIO;
import org.usfirst.frc4048.common.util.LoggableSystem;
import org.littletonrobotics.junction.Logger;
import org.usfirst.frc4048.common.util.SwerveModuleProfile;

public class SwerveDrivetrain extends SubsystemBase {
    public static final SwerveModuleProfile SWERVE_MODULE_PROFILE = SwerveModuleProfile.MK4;
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;
    private final Translation2d frontLeftLocation = new Translation2d(Constants.ROBOT_LENGTH / 2, Constants.ROBOT_WIDTH / 2);
    private final Translation2d frontRightLocation = new Translation2d(Constants.ROBOT_LENGTH / 2, -Constants.ROBOT_WIDTH / 2);
    private final Translation2d backLeftLocation = new Translation2d(-Constants.ROBOT_LENGTH / 2, Constants.ROBOT_WIDTH / 2);
    private final Translation2d backRightLocation = new Translation2d(-Constants.ROBOT_LENGTH / 2, -Constants.ROBOT_WIDTH / 2);
    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation);
    private final LoggableSystem<GyroIO, GyroInputs> gyroSystem;
    private DriveMode driveMode = DriveMode.FIELD_CENTRIC;
    private final PoseEstimator poseEstimator;

    public SwerveDrivetrain(SwerveModule frontLeftModule, SwerveModule frontRightModule, SwerveModule backLeftModule, SwerveModule backRightModule, GyroIO gyroIO, LoggableIO<ApriltagInputs> apriltagIO) {
        this.frontLeft = frontLeftModule;
        this.frontRight = frontRightModule;
        this.backLeft = backLeftModule;
        this.backRight = backRightModule;
        this.gyroSystem = new LoggableSystem<>(gyroIO, new GyroInputs());
        this.poseEstimator = new PoseEstimator(frontLeft, frontRight, backLeft, backRight, apriltagIO, kinematics, getLastGyro());
    }

    @Override
    public void periodic() {
        poseEstimator.updateInputs();
        processInputs();
        OdometryMeasurement odom = new OdometryMeasurement(
                new SwerveModulePosition[]{
                    frontLeft.getPosition(),
                    frontRight.getPosition(),
                    backLeft.getPosition(),
                    backRight.getPosition()
            }, getLastGyro()
        );
        Logger.recordOutput("LastOdomModPoses", odom.modulePosition());
        poseEstimator.updatePosition(odom);
        poseEstimator.updateVision();
        Logger.recordOutput("realSwerveStates",
                frontLeft.getLatestState(),
                frontRight.getLatestState(),
                backLeft.getLatestState(),
                backRight.getLatestState()
        );
    }

    private void processInputs() {
        frontLeft.updateInputs();
        frontRight.updateInputs();
        backLeft.updateInputs();
        backRight.updateInputs();
        gyroSystem.updateInputs();
    }

    public ChassisSpeeds createChassisSpeeds(double xSpeed, double ySpeed, double rotation, DriveMode driveMode) {
        return driveMode.equals(DriveMode.FIELD_CENTRIC)
                ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rotation, Rotation2d.fromDegrees(getLastGyro()))
                : new ChassisSpeeds(xSpeed, ySpeed, rotation);
    }

    public void drive(ChassisSpeeds speeds) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(speeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.MAX_VELOCITY);
        setModuleStates(swerveModuleStates);
    }


    public ChassisSpeeds speedsFromStates() {
        return kinematics.toChassisSpeeds(frontLeft.getLatestState(), frontRight.getLatestState(), backLeft.getLatestState(), backRight.getLatestState());
    }

    private void setModuleStates(SwerveModuleState[] desiredStates) {
        Logger.recordOutput("desiredStates", desiredStates);
        frontLeft.setState(desiredStates[0]);
        frontRight.setState(desiredStates[1]);
        backLeft.setState(desiredStates[2]);
        backRight.setState(desiredStates[3]);
    }

    public void stopMotor() {
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void zeroRelativeEncoders() {
        frontLeft.resetRelativeEnc();
        frontRight.resetRelativeEnc();
        backLeft.resetRelativeEnc();
        backRight.resetRelativeEnc();
    }

    public void setSteerOffset(double absEncoderZeroFL, double absEncoderZeroFR, double absEncoderZeroBL, double absEncoderZeroBR) {
        frontLeft.setSteerOffset(absEncoderZeroFL);
        frontRight.setSteerOffset(absEncoderZeroFR);
        backLeft.setSteerOffset(absEncoderZeroBL);
        backRight.setSteerOffset(absEncoderZeroBR);
    }

    public void resetGyro() {
        gyroSystem.getIO().resetGyro();
    }

    public double getLastGyro() {
        return gyroSystem.getInputs().anglesInDeg;
    }

    public void setDriveMode(DriveMode driveMode) {
        this.driveMode = driveMode;
    }

    public DriveMode getDriveMode() {
        return driveMode;
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPose();
    }

    public void setGyroOffset(double offset) {
        gyroSystem.getIO().setAngleOffset(offset);
    }

    public void resetOdometry(Pose2d startingPosition) {
        poseEstimator.resetOdometry(startingPosition.getRotation().getRadians(), startingPosition.getTranslation());
    }

    public Rotation2d getGyroAngle() {
        return Rotation2d.fromDegrees(getLastGyro());
    }

    public ChassisSpeeds getChassisSpeeds() {
        return kinematics.toChassisSpeeds(frontLeft.getLatestState(), frontRight.getLatestState(), backLeft.getLatestState(), backRight.getLatestState());
    }

    public ChassisSpeeds getFieldChassisSpeeds() {
        return ChassisSpeeds.fromRobotRelativeSpeeds(getChassisSpeeds(), getPose().getRotation());
    }
}


