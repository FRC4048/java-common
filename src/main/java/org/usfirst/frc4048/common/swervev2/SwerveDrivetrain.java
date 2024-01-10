
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


public class SwerveDrivetrain extends SubsystemBase {
    private final CANSparkMax frontLeftDrive;
    private final CANSparkMax frontLeftTurn;
    private final CANSparkMax backLeftDrive;
    private final CANSparkMax backLeftTurn;
    private final CANSparkMax frontRightDrive;
    private final CANSparkMax frontRightTurn;
    private final CANSparkMax backRightDrive;
    private final CANSparkMax backRightTurn;

    private final WPI_CANCoder frontLeftCanCoder;
    private final WPI_CANCoder backLeftCanCoder;
    private final WPI_CANCoder frontRightCanCoder;
    private final WPI_CANCoder backRightCanCoder;


    private final SparkMaxSwerveModule frontLeft;
    private final SparkMaxSwerveModule frontRight;
    private final SparkMaxSwerveModule backLeft;
    private final SparkMaxSwerveModule backRight;

    private final Translation2d frontLeftLocation = new Translation2d(Constants.ROBOT_LENGTH/2, Constants.ROBOT_WIDTH/2);
    private final Translation2d frontRightLocation = new Translation2d(Constants.ROBOT_LENGTH/2, -Constants.ROBOT_WIDTH/2);
    private final Translation2d backLeftLocation = new Translation2d(-Constants.ROBOT_LENGTH/2, Constants.ROBOT_WIDTH/2);
    private final Translation2d backRightLocation = new Translation2d(-Constants.ROBOT_LENGTH/2, -Constants.ROBOT_WIDTH/2);
    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(frontLeftLocation,frontRightLocation,backLeftLocation,backRightLocation);

    private double gyroOffset = 180;
    private final AHRS navxGyro;
    private double navxGyroValue;
    private float filterRoll = 0;
    

    private double getGyro() {
        return (navxGyro.getAngle() % 360)*-1; //ccw should be positive
    }

    public SwerveDrivetrain() {
        navxGyro = new AHRS();
        navxGyroValue = -1;

        frontLeftDrive = new CANSparkMax(Constants.DRIVE_FRONT_LEFT_D, CANSparkMaxLowLevel.MotorType.kBrushless);
        frontLeftTurn = new CANSparkMax(Constants.DRIVE_FRONT_LEFT_S, CANSparkMaxLowLevel.MotorType.kBrushless);
        backLeftDrive = new CANSparkMax(Constants.DRIVE_BACK_LEFT_D, CANSparkMaxLowLevel.MotorType.kBrushless);
        backLeftTurn = new CANSparkMax(Constants.DRIVE_BACK_LEFT_S, CANSparkMaxLowLevel.MotorType.kBrushless);
        
        frontRightDrive = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT_D, CANSparkMaxLowLevel.MotorType.kBrushless);
        frontRightTurn = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT_S, CANSparkMaxLowLevel.MotorType.kBrushless);
        backRightDrive = new CANSparkMax(Constants.DRIVE_BACK_RIGHT_D, CANSparkMaxLowLevel.MotorType.kBrushless);
        backRightTurn = new CANSparkMax(Constants.DRIVE_BACK_RIGHT_S, CANSparkMaxLowLevel.MotorType.kBrushless);

        frontLeftCanCoder = new WPI_CANCoder(Constants.DRIVE_CANCODER_FRONT_LEFT);
        backLeftCanCoder = new WPI_CANCoder(Constants.DRIVE_CANCODER_BACK_LEFT);
        frontRightCanCoder = new WPI_CANCoder(Constants.DRIVE_CANCODER_FRONT_RIGHT);
        backRightCanCoder = new WPI_CANCoder(Constants.DRIVE_CANCODER_BACK_RIGHT);
        double driveVelConvFactor = (2 * Constants.WHEEL_RADIUS * Math.PI) / (Constants.CHASSIS_DRIVE_GEAR_RATIO * 60);
        double drivePosConvFactor = (2 * Constants.WHEEL_RADIUS * Math.PI) / (Constants.CHASSIS_DRIVE_GEAR_RATIO);
        double steerPosConvFactor = 2 * Math.PI / Constants.CHASSIS_STEER_GEAR_RATIO;
        
        EncodedSwerveSparkMax encodedSwerveSparkMaxFL = new EncodedSwerveSparkMax(frontLeftDrive, frontLeftTurn, frontLeftCanCoder, driveVelConvFactor, drivePosConvFactor, steerPosConvFactor);
        EncodedSwerveSparkMax encodedSwerveSparkMaxFR = new EncodedSwerveSparkMax(frontRightDrive, frontRightTurn, frontRightCanCoder, driveVelConvFactor, drivePosConvFactor, steerPosConvFactor);
        EncodedSwerveSparkMax encodedSwerveSparkMaxBL = new EncodedSwerveSparkMax(backLeftDrive, backLeftTurn, backLeftCanCoder, driveVelConvFactor, drivePosConvFactor, steerPosConvFactor);
        EncodedSwerveSparkMax encodedSwerveSparkMaxBR = new EncodedSwerveSparkMax(backRightDrive, backRightTurn, backRightCanCoder, driveVelConvFactor, drivePosConvFactor, steerPosConvFactor);
        TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(Constants.MAX_ANGULAR_SPEED * 4, MODULE_MAX_ANGULAR_ACCELERATION * 10);
        PID drivePid = PID.of(Constants.DRIVE_PID_P,Constants.DRIVE_PID_I,Constants.DRIVE_PID_D);
        PID steerPid = PID.of(Constants.STEER_PID_P,Constants.STEER_PID_I,Constants.STEER_PID_D);
        Gain driveGain = Gain.of(Constants.DRIVE_PID_FF_V,Constants.DRIVE_PID_FF_S);
        Gain steerGain = Gain.of(Constants.STEER_PID_FF_V,Constants.STEER_PID_FF_S);
        frontLeft = new SparkMaxSwerveModule(encodedSwerveSparkMaxFL, drivePid,steerPid, driveGain,steerGain,constraints);
        frontRight = new SparkMaxSwerveModule(encodedSwerveSparkMaxFR, drivePid,steerPid, driveGain,steerGain,constraints);
        backLeft = new SparkMaxSwerveModule(encodedSwerveSparkMaxBL, drivePid,steerPid, driveGain,steerGain,constraints);
        backRight = new SparkMaxSwerveModule(encodedSwerveSparkMaxBR, drivePid,steerPid, driveGain,steerGain,constraints);
        frontLeftDrive.setInverted(true);
        frontRightDrive.setInverted(false);
        backRightDrive.setInverted(false);
        backLeftDrive.setInverted(true);
        navxGyro.setAngleAdjustment(gyroOffset);
    }

    @Override
    public void periodic() {
        navxGyroValue = getGyro();
    }
    public float getRoll() {
        return navxGyro.getRoll();
    }
    
    private static final double MODULE_MAX_ANGULAR_ACCELERATION =
            2 * Math.PI; // radians per second squared
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, new Rotation2d(Math.toRadians(navxGyroValue)))
                        : new ChassisSpeeds(xSpeed, ySpeed, rot));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.MAX_VELOCITY);
        setModuleStates(swerveModuleStates);
    }
    public void driveRaw(double speed){
        SwerveModuleState swerveModuleState = new SwerveModuleState(speed, new Rotation2d(0));
        frontLeft.setDesiredState(swerveModuleState);
        frontRight.setDesiredState(swerveModuleState);
        backLeft.setDesiredState(swerveModuleState);
        backRight.setDesiredState(swerveModuleState);
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }
    
    public void stopMotor() {
        frontLeftTurn.set(0.0);
        frontLeftDrive.set(0.0);
        frontRightTurn.set(0.0);
        frontRightDrive.set(0.0);
        backLeftTurn.set(0.0);
        backLeftDrive.set(0.0);
        backRightTurn.set(0.0);
        backRightDrive.set(0.0);
    }

    public void SetRelEncZero(){
        frontLeft.getSwerveMotor().resetRelEnc();
    }

    public double getRelEnc(){
        return frontLeft.getSwerveMotor().getDriveEncPosition();
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
}
*/
