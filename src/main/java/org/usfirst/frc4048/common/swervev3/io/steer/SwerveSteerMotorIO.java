package org.usfirst.frc4048.common.swervev3.io.steer;

import org.usfirst.frc4048.common.util.LoggableIO;

public interface SwerveSteerMotorIO extends LoggableIO<SwerveSteerMotorInput> {
    void setSteerVoltage(double volts);
    void setSteerOffset(double zeroAbs, double absCurrentPose);
    void resetEncoder();
}
