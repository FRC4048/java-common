package org.usfirst.frc4048.common.swervev3.bags;

import edu.wpi.first.math.kinematics.SwerveModulePosition;

/**
 * Class containing odometry state for a given measurement.
 * @param modulePosition Array of swerve module positions for a single reading in the order [FrontLeft, FrontRight, BackLeft, BackRight]
 * @param gyroValueDeg gyro value in degrees during the reading
 */
public record OdometryMeasurement(SwerveModulePosition[] modulePosition, double gyroValueDeg) {
}
