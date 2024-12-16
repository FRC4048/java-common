package org.usfirst.frc4048.common.apriltags;

public record ApriltagReading(double posX, double posY, double rotationDeg, double latency, double measurementTime) {
}
