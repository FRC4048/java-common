package org.usfirst.frc4048.common.swervev3.io.drive;

import org.usfirst.frc4048.common.util.LoggableIO;

public interface SwerveDriveMotorIO extends LoggableIO<SwerveDriveMotorInput> {
    void setDriveVoltage(double volts);
    void resetEncoder();
}
