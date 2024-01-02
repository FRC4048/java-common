package org.usfirst.frc4048.common.auto.event;

import org.usfirst.frc4048.common.auto.AutoAction;
import org.usfirst.frc4048.common.auto.FieldLocation;

public interface AutoEventProvider {
    AutoAction getSelectedAction();
    FieldLocation getSelectedLocation();
    AutoAction getDefaultActionOption();
    FieldLocation getDefaultLocationOption();

}
