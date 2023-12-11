package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.AbstractMap;
import java.util.HashMap;

public interface AutoChooser {
     void setOptions();
     AutoAction getDefaultOption();
     
     Command getAutoCommand();
}
