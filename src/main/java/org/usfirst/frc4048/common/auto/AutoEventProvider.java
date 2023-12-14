package org.usfirst.frc4048.common.auto;

public interface AutoEventProvider {
    AutoAction getSelectedAction();
    FieldLocation getSelectedLocation();
    AutoAction getDefaultActionOption();
    FieldLocation getDefaultLocationOption();

}
