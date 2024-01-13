package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.event.AutoEventProvider;

public interface AutoChooser {
     Command getAutoCommand();
     AutoEventProvider getAutoEventProvider();

}
