package org.usfirst.frc4048.common.swervev3.bags;

import edu.wpi.first.math.geometry.Pose2d;
import org.usfirst.frc4048.common.util.Apriltag;

public record VisionMeasurement(Pose2d measurement, Apriltag tag, double timeOfMeasurement)  {
}
