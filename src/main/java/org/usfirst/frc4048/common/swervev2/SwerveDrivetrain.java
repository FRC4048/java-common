package org.usfirst.frc4048.common.swervev2;

import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.usfirst.frc4048.common.swervev2.components.EncodedSwerveSparkMax;
import org.usfirst.frc4048.common.swervev2.type.SparkMaxSwerveModule;
import org.usfirst.frc4048.common.util.Gain;
import org.usfirst.frc4048.common.util.PID;

public class SwerveDrivetrain {
    private final CANSparkMax m_frontLeftDrive;
    private final CANSparkMax m_frontLeftTurn;

    private final WPI_CANCoder frontLeftCanCoder;

    private final Translation2d m_frontLeftLocation = new Translation2d(0,0);

    private final SparkMaxSwerveModule m_frontLeft;


    private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(m_frontLeftLocation);



    public SwerveDrivetrain() {

        m_frontLeftDrive = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);

        m_frontLeftTurn = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);

        frontLeftCanCoder = new WPI_CANCoder(0);
        double driveVelConvFactor = (2 * 0) / (0 * 60);
        double drivePosConvFactor = (2 * 0) / (0);
        double steerPosConvFactor = (2 * Math.PI / 0);
        EncodedSwerveSparkMax encodedSwerveSparkMax = new EncodedSwerveSparkMax(m_frontLeftDrive, m_frontLeftTurn, frontLeftCanCoder, driveVelConvFactor, drivePosConvFactor, steerPosConvFactor);
        TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints(18 * 4, 2 * Math.PI * 10);
        m_frontLeft = new SparkMaxSwerveModule(encodedSwerveSparkMax, PID.of(0,0,0),PID.of(0,0,0), Gain.of(0,0),Gain.of(0,0),constraints);
        m_frontLeftDrive.setInverted(true);
    }


    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        SwerveModuleState[] swerveModuleStates = m_kinematics.toSwerveModuleStates(
                fieldRelative
                        ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, new Rotation2d(Math.toRadians(0)))
                        : new ChassisSpeeds(xSpeed, ySpeed, rot));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, 0);
        m_frontLeft.setDesiredState(swerveModuleStates[0]);
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        m_frontLeft.setDesiredState(desiredStates[0]);
    }


    public void stopMotor() {
        m_frontLeftTurn.set(0.0);
    }


    public void setPower(double value){
        m_frontLeftTurn.set(value);
    }

    public void SetRelEncZero(){
        m_frontLeft.getSwerveMotor().resetRelEnc();
    }


    public double getRelEnc(){
        return m_frontLeft.getSwerveMotor().getDriveEncPosition();
    }
}
