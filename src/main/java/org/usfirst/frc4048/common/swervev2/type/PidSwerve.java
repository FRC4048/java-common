package org.usfirst.frc4048.common.swervev2.type;

public interface PidSwerve {
    double calcDrivePidOut(double setpoint);
    double calcSteerPidOut(double goal);
    double calcDriveFeedForward(double velocity);
    double calcSteerFeedForward();
}
