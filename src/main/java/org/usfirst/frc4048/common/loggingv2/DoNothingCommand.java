package org.usfirst.frc4048.common.loggingv2;

public class DoNothingCommand extends LoggableCommand {
    @Override
    public boolean isFinished() {
        return true;
    }

}
