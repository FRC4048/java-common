package org.usfirst.frc4048.common.gyro;

import com.kauailabs.navx.frc.AHRS;

public class RealGyroIO implements GyroIO {
    private final ThreadedGyro gyro;

    public RealGyroIO(ThreadedGyro gyro) {
        this.gyro = gyro;
    }

    @Override
    public void setAngleOffset(double offset) {
        gyro.setAngleAdjustment(offset);
    }

    @Override
    public void resetGyro() {
        gyro.resetGyro();
    }

    @Override
    public void updateInputs(GyroInputs inputs) {
        inputs.anglesInDeg = gyro.getGyroValue();
        inputs.angleOffset = gyro.getAngleOffset();
    }
}
