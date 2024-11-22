package org.usfirst.frc4048.common.swervev3.bags;

import edu.wpi.first.math.kinematics.SwerveModulePosition;

public record OdometryMeasurement(SwerveModulePosition[] modulePosition, double gyroValueDeg) {
}
