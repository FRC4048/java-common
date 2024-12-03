package org.usfirst.frc4048.common.swervev3.io.steer;

import com.revrobotics.CANSparkMax;
import org.usfirst.frc4048.common.swervev2.KinematicsConversionConfig;

import java.util.concurrent.atomic.AtomicLong;

public class MockSteerMotorIO implements SwerveSteerMotorIO{

    @Override
    public void setSteerVoltage(double volts) {

    }

    @Override
    public void setSteerOffset(double zeroAbs, double absCurrentPose) {

    }

    @Override
    public void resetEncoder() {

    }

    @Override
    public void updateInputs(SwerveSteerMotorInput inputs) {

    }
}
