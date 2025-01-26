package org.usfirst.frc4048.common.util;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.config.LimitSwitchConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import static com.revrobotics.spark.SparkBase.PersistMode.kNoPersistParameters;
import static com.revrobotics.spark.SparkBase.PersistMode.kPersistParameters;
import static com.revrobotics.spark.SparkBase.ResetMode.kNoResetSafeParameters;
import static com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters;
import static com.revrobotics.spark.config.MAXMotionConfig.MAXMotionPositionMode.kMAXMotionTrapezoidal;
import static com.revrobotics.spark.config.SparkBaseConfig.IdleMode.kBrake;

/**
 * A Wrapper utility to encapsulate the NEO motor with PID capability.
 * This is simply a wrapper with some convenient defaults and initializations that make programming
 * the PID of the NEO easier.
 * <p>
 * TODO: This does not yet support the external absolute encoder that may be needed
 * TODO: This does not yet support velocity PID or other advanced features
 */
public class NeoPidMotor {
    public static final double DEFAULT_P = 1e-2;
    public static final double DEFAULT_I = 0;
    public static final double DEFAULT_D = 0.0;
    public static final double DEFAULT_IZONE = 0.0;
    public static final double DEFAULT_FF = 0.0;
    public static final double RAMP_RATE = 0;
    public static final int MAX_VELOCITY = 5000;
    public static final int MAX_ACCELERATION = 10000;
    public static final double ALLOWED_ERROR = 1.0;

    // The neo motor controller
    private final SparkMax neoMotor;
    // The built-in relative encoder
    private final RelativeEncoder encoder;
    // The built-in PID controller
    private final SparkClosedLoopController pidController;

    // The desired motor position
    private double setPosition = 0.0;

    /**
     * Constructor using reasonable default values
     * @param id the CAN ID for the controller
     */
    public NeoPidMotor(int id) {
        this(id, DEFAULT_P, DEFAULT_I, DEFAULT_D, DEFAULT_IZONE, DEFAULT_FF);
    }

    public NeoPidMotor(int id, double pidP, double pidI, double pidD, double iZone, double pidFF) {
        neoMotor = new SparkMax(id, SparkLowLevel.MotorType.kBrushless);
        encoder = neoMotor.getEncoder();

        pidController = neoMotor.getClosedLoopController();
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(40)
                .closedLoopRampRate(RAMP_RATE)
                .idleMode(kBrake);
        config.closedLoop
                .feedbackSensor(ClosedLoopConfig.FeedbackSensor.kPrimaryEncoder)
                .pid(pidP, pidI, pidD)
                .velocityFF(pidFF)
                .iZone(iZone)
                .outputRange(-1, 1)
                .maxMotion
                .maxVelocity(MAX_VELOCITY)
                .maxAcceleration(MAX_ACCELERATION)
                .positionMode(kMAXMotionTrapezoidal)
                .allowedClosedLoopError(ALLOWED_ERROR);
        config.limitSwitch
                .forwardLimitSwitchEnabled(true)
                .forwardLimitSwitchType(LimitSwitchConfig.Type.kNormallyOpen)
                .reverseLimitSwitchEnabled(true)
                .forwardLimitSwitchType(LimitSwitchConfig.Type.kNormallyOpen);

        neoMotor.configure(config, kResetSafeParameters, kPersistParameters);
    }

    /**
     * Set the desired position using the relative encoder as a reference
     * @param position the desired motor position
     */
    public void setPidPos(double position) {
        pidController.setReference(position, SparkBase.ControlType.kMAXMotionPositionControl);
        this.setPosition = position;
    }

    public double getPidPosition() {
        return setPosition;
    }

    public void setPid(double pidP, double pidI, double pidD) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.closedLoop
                .pid(pidP, pidI, pidD);
        neoMotor.configure(config, kNoResetSafeParameters, kNoPersistParameters);
    }

    public void setPid(double pidP, double pidI, double pidD, double iZone, double pidFF) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.closedLoop
                .pid(pidP, pidI, pidD)
                .velocityFF(pidFF)
                .iZone(iZone);
        neoMotor.configure(config, kNoResetSafeParameters, kNoPersistParameters);
    }

    public SparkMax getNeoMotor() {
        return neoMotor;
    }

    public RelativeEncoder getEncoder() {
        return encoder;
    }

    public SparkClosedLoopController getPidController() {
        return pidController;
    }
}
