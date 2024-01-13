package org.usfirst.frc4048.common.auto.chooser;

import frc.robot.auto.AutoAction;
import frc.robot.auto.FieldLocation;
import frc.robot.auto.event.AutoEventProvider;
import frc.robot.auto.event.Nt4AutoEventProvider;

public abstract class Nt4AutoChooser implements AutoChooser{
    private final Nt4AutoEventProvider provider = new Nt4AutoEventProvider(AutoAction.DoNothing, FieldLocation.Middle);
    @Override
    public AutoEventProvider getAutoEventProvider() {
        return provider;
    }

}
