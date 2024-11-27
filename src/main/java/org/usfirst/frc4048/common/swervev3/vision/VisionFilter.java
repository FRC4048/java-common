package org.usfirst.frc4048.common.swervev3.vision;


import org.usfirst.frc4048.common.swervev3.bags.VisionMeasurement;

import java.util.LinkedHashMap;
import java.util.Queue;

public interface VisionFilter {
    LinkedHashMap<VisionMeasurement, FilterResult> filter(Queue<VisionMeasurement> measurements);
}
