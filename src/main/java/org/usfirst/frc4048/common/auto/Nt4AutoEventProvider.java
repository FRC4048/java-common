package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import java.util.Arrays;

public class Nt4AutoEventProvider implements AutoEventProvider {

    private final SendableChooser<AutoAction> actionChooser;
    private final SendableChooser<FieldLocation> locationChooser;

    private final AutoAction defaultAutoAction;
    private final FieldLocation defaultFieldLocation;

    public Nt4AutoEventProvider(AutoAction defaultAutoAction, FieldLocation defaultFieldLocation) {
        this.defaultAutoAction = defaultAutoAction;
        this.defaultFieldLocation = defaultFieldLocation;
        this.actionChooser = new SendableChooser<>();
        this.locationChooser = new SendableChooser<>();
        Arrays.stream(AutoAction.values()).forEach(a -> actionChooser.addOption(a.getName(), a));
        Arrays.stream(FieldLocation.values()).forEach(l -> locationChooser.addOption(l.name(), l));
        actionChooser.setDefaultOption(getDefaultActionOption().getName(), getDefaultActionOption());
        locationChooser.setDefaultOption(getDefaultLocationOption().name(), getDefaultLocationOption());
    }
    public AutoAction getSelectedAction() {
        return actionChooser.getSelected() == null ? getDefaultActionOption() : actionChooser.getSelected();
    }

    public FieldLocation getSelectedLocation() {
        return locationChooser.getSelected() == null ? getDefaultLocationOption() : locationChooser.getSelected();
    }

    @Override
    public AutoAction getDefaultActionOption() {
        return defaultAutoAction;
    }

    @Override
    public FieldLocation getDefaultLocationOption() {
        return defaultFieldLocation;
    }
}
