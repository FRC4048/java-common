package org.usfirst.frc4048.common.swervev2.type;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import org.usfirst.frc4048.common.swervev2.components.EncodedSwerveSparkMax;
import org.usfirst.frc4048.common.util.Gain;
import org.usfirst.frc4048.common.util.PID;

public class SparkMaxSwerveModule extends GenericSwerveModule {
    public SparkMaxSwerveModule(EncodedSwerveSparkMax swerveMotors, PID drivePid, PID turnPid, Gain driveGain, Gain turnGain, TrapezoidProfile.Constraints goalConstraint) {
        super(swerveMotors, drivePid, turnPid, driveGain, turnGain, goalConstraint);
    }
}
