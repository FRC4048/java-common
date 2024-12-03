package org.usfirst.frc4048.common.swervev3.bags;

import edu.wpi.first.math.geometry.Pose2d;
import org.usfirst.frc4048.common.util.Apriltag;

/**
 * @param measurement Estimated position of robot received from vision
 * @param tag the AprilTag that the robot saw
 * @param timeOfMeasurement timestamp in seconds that the vision pose was RECORDED
 */
public record VisionMeasurement(Pose2d measurement, Apriltag tag, double timeOfMeasurement)  {
}
