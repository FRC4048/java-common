package org.usfirst.frc4048.common.lightstrip;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;
import org.usfirst.frc4048.common.util.BlinkinPattern;

public class LightStripInputs implements LoggableInputs {
    public BlinkinPattern pattern = BlinkinPattern.BLACK;

    @Override
    public void toLog(LogTable table) {
        table.put("patternPWM", pattern.getPwm());
    }

    @Override
    public void fromLog(LogTable table) {
        pattern = BlinkinPattern.of(table.get("patternPWM", pattern.getPwm()));
    }
}
