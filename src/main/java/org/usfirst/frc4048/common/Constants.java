package org.usfirst.frc4048.common;

import edu.wpi.first.math.util.Units;

/**
 * Demo constants class to allow the other classes to compile
 */
public class Constants {
    public static final double ROBOT_LENGTH = 1.0;
    public static final double ROBOT_WIDTH = 1.0;
    public static final double MAX_VELOCITY = 1.0;
    public static final boolean ENABLE_LOGGING = true;
    public static final double CAMERA_OFFSET_FROM_CENTER_X = 0;
    public static final double CAMERA_OFFSET_FROM_CENTER_Y = 0;
    public static final int DRIVE_SMART_LIMIT = 0;
    public static final double DRIVE_SECONDARY_LIMIT = 0;
    public static final double DRIVE_RAMP_RATE_LIMIT = 0;
    public static final int SERVER_SOCKET_CONNECTION_TIMEOUT = 2000;
    public static final int SERVER_SOCKET_ATTEMPT_DELAY = 100;
    public static final long GYRO_THREAD_RATE_MS = 10;
    public static final boolean ENABLE_VISION = true;
    public static final long POSE_BUFFER_STORAGE_TIME = 2;
    public static final double VISION_CONSISTANCY_THRESHOLD = 0.25;
    public static final long MAX_LOG_TIME_WAIT = 10;
    public static final int LIGHTSTRIP_PORT = 0;

    public static final double kElevatorGearing = 10.0;
    public static final double kElevatorDrumRadius = Units.inchesToMeters(2.0);
    public static final double kCarriageMass = 4.0; // kg
    public static final double kMinElevatorHeightMeters = 0.0;
    public static final double kMaxElevatorHeightMeters = 10.0;
}
