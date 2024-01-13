package org.usfirst.frc4048.common.auto.event;


import frc.robot.auto.AutoAction;
import frc.robot.auto.FieldLocation;

public interface AutoEventProvider {
    AutoAction getSelectedAction();
    FieldLocation getSelectedLocation();
    AutoAction getDefaultActionOption();
    FieldLocation getDefaultLocationOption();

}
