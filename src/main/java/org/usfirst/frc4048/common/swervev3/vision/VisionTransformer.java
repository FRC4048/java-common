package org.usfirst.frc4048.common.swervev3.vision;

import edu.wpi.first.math.geometry.Pose2d;
import org.usfirst.frc4048.common.swervev3.bags.VisionMeasurement;

public interface VisionTransformer {
    Pose2d getVisionPose(VisionMeasurement measurement);
}
