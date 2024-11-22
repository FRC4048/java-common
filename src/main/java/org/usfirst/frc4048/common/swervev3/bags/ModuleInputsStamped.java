package org.usfirst.frc4048.common.swervev3.bags;

public record ModuleInputsStamped(double steerEncoderPosition, double driveEncoderPosition,
                                  double driveEncoderVelocity, double steerEncoderVelocity,
                                  double measurementTimestamp) {
}
