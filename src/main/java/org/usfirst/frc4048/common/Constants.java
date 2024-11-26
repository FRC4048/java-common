package org.usfirst.frc4048.common;

import org.usfirst.frc4048.common.util.SwerveModuleProfile;

/**
 * Demo constants class to allow the other classes to compile
 */
public class Constants extends GameConstants{
    public static final double ROBOT_LENGTH = 1.0;
    public static final double ROBOT_WIDTH = 1.0;
    public static final double MAX_VELOCITY = 1.0;
    public static final boolean ENABLE_LOGGING = true;
    public static final double CAMERA_OFFSET_FROM_CENTER_X = 0;
    public static final double CAMERA_OFFSET_FROM_CENTER_Y = 0;
    public static final int DRIVE_SMART_LIMIT = 0;
    public static final double DRIVE_SECONDARY_LIMIT = 0;
    public static final double DRIVE_RAMP_RATE_LIMIT = 0;
    public static final SwerveModuleProfile SWERVE_MODULE_PROFILE = SwerveModuleProfile.MK4;
    public static final int SERVER_SOCKET_CONNECTION_TIMEOUT = 2000;
    public static final int SERVER_SOCKET_ATTEMPT_DELAY = 100;
    public static final long GYRO_THREAD_RATE_MS = 10;
}
