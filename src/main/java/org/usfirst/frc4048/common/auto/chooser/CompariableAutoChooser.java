package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.auto.event.AutoEventComparer;
import org.usfirst.frc4048.common.auto.event.AutoEventProvider;

import java.util.Map;

public abstract class CompariableAutoChooser implements AutoChooser {
    abstract Map<AutoEventComparer, Command> getCommandMap();
    public abstract Command getDefaultCommand();
    public abstract AutoEventProvider getAutoEventProvider();

}
