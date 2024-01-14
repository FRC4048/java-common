package org.usfirst.frc4048.common.autochooser.event;


import org.usfirst.frc4048.common.autochooser.AutoAction;
import org.usfirst.frc4048.common.autochooser.FieldLocation;

public interface AutoEventProvider {
    AutoAction getSelectedAction();
    FieldLocation getSelectedLocation();
    AutoAction getDefaultActionOption();
    FieldLocation getDefaultLocationOption();

}
