package org.usfirst.frc4048.common.swervev3.io;


import org.usfirst.frc4048.common.util.LoggableIO;

public interface ModuleIO extends LoggableIO<SwerveModuleInput> {
    void setDriveVoltage(double volts);
    void setSteerVoltage(double volts);
    void setSteerOffset(double offset);
    void resetEncoder();
}
