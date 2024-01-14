package frc.robot.auto.event;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.auto.AutoAction;
import frc.robot.auto.FieldLocation;

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
        ShuffleboardTab autoTab = Shuffleboard.getTab("Auto");
        autoTab.add("Auto Action",actionChooser).withPosition(0,0).withSize(4,1);
        autoTab.add("Location Chooser",locationChooser).withPosition(0,1).withSize(4,1);;

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
