package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.usfirst.frc4048.common.auto.event.AutoEventComparer;
import org.usfirst.frc4048.common.auto.event.AutoEventProvider;

import java.util.*;

public interface AutoChooser {
     Command getAutoCommand();
     Map<AutoEventComparer, Command> getCommandMap();
     CommandBase getDefaultCommand();
     AutoEventProvider getAutoEventProvider();

}
