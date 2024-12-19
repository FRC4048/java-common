package org.usfirst.frc4048.common.lightstrip;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import org.usfirst.frc4048.common.Constants;
import org.usfirst.frc4048.common.util.BlinkinPattern;

public class RealLightStripIO implements LightStripIO {

    private final Spark colorSensorPort;

    public RealLightStripIO() {
        colorSensorPort = new Spark(Constants.LIGHTSTRIP_PORT);
    }

    @Override
    public void setPattern(BlinkinPattern pattern) {
        colorSensorPort.set(pattern.getPwm());
    }

    @Override
    public void updateInputs(LightStripInputs inputs) {
        inputs.pattern = BlinkinPattern.of(colorSensorPort.get());
    }
}
