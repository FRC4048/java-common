package org.usfirst.frc4048.common.swervev3.bags;

import edu.wpi.first.math.geometry.Pose2d;
import org.usfirst.frc4048.common.util.Apriltag;

/**
 * @param measurement estimated robot position calculated from apriltag
 * @param tag what tag produced the position measurement
 * @param timeOfMeasurement difference between the time the measurement was received by the robot program and when it was sent over the network
 */
public record VisionMeasurement(Pose2d measurement, Apriltag tag, double timeOfMeasurement)  {
}
