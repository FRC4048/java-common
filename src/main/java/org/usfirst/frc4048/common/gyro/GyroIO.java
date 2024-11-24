package org.usfirst.frc4048.common.gyro;


import org.usfirst.frc4048.common.util.LoggableIO;

public interface GyroIO extends LoggableIO<GyroInputs> {
    void setAngleOffset(double offset);
    void resetGyro();
}
