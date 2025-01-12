package org.usfirst.frc4048.common.lightstrip;


import org.usfirst.frc4048.common.util.BlinkinPattern;
import org.usfirst.frc4048.common.util.LoggableIO;

public interface LightStripIO extends LoggableIO<LightStripInputs> {
    void setPattern(BlinkinPattern pattern);
}
