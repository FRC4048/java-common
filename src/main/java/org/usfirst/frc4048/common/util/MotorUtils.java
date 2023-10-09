package org.usfirst.frc4048.common.util;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.utils.logging.Logging;

/*
 *   MotorStall object should be instantiated in init() method of a command
 *   isFinished() should call isStalled() to determine if stalled for longer than allowed
 */
public class MotorUtils {
    public static final double DEFAULT_TIMEOUT = 0.15;
    private double timeout;
    private double time;
    private PowerDistribution powerDistPanel;

    final int PDPChannel;
    final double currentThreshold;

    private boolean everStalled = false;

    public MotorUtils(int PDPPort, double currentThreshold, double timeout, PowerDistribution powerDistPanel ){
        this.timeout = timeout;
        this.PDPChannel = PDPPort;
        this.currentThreshold = currentThreshold;
        this.powerDistPanel = powerDistPanel;
        time = Timer.getFPGATimestamp();
    }

    public boolean isStalled() {
        final double currentValue = powerDistPanel.getCurrent(PDPChannel);
        final double now = Timer.getFPGATimestamp();

        if (currentValue < currentThreshold) {
            time = now;
        } else {
            DriverStation.reportError("Motor stall, PDP Channel=" + PDPChannel, false);
            if (now - time > timeout) {
                everStalled = true;
                Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "Motor stall, PDP channel =" + PDPChannel);
                return true;
            }
        }
        return false;
    }

    public boolean everStalled() {
        return everStalled;
    }

    public void resetStall() {
        everStalled = false;
    }
}
