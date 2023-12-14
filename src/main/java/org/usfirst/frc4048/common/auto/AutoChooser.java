package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.*;
import java.util.stream.Collectors;

public interface AutoChooser {
     Command getAutoCommand();
     Map<AutoEventComparer, Command> getCommandMap();
     CommandBase getDefaultCommand();
     AutoEventProvider getAutoEventProvider();

}
