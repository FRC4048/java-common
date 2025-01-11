package org.usfirst.frc4048.common.autochooserv2.chooser;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.usfirst.frc4048.common.autochooserv2.event.AutoEvent;
import org.usfirst.frc4048.common.autochooserv2.event.AutoEventProvider;
import org.usfirst.frc4048.common.autochooserv2.AutoAction;
import org.usfirst.frc4048.common.autochooserv2.FieldLocation;
import org.usfirst.frc4048.common.autochooserv2.event.AutoEventProviderIO;

import java.util.HashMap;
import java.util.Map;

public class AutoChooser2024 extends SubsystemBase implements AutoChooser {
    private final Map<AutoEvent, Command> commandMap;
    private final AutoEventProvider provider;

    public AutoChooser2024(AutoEventProviderIO providerIO) {
        this.provider = new AutoEventProvider(providerIO, this::isValid);
        commandMap = new HashMap<>();
    }

    @Override
    public void periodic() {
        provider.updateInputs();
    }

    public Command getAutoCommand() {
        return commandMap.get(new AutoEvent(provider.getSelectedAction(), provider.getSelectedLocation()));
    }

    @Override
    public Pose2d getStartingPosition() {
        return provider.getSelectedLocation().getLocation();
    }

    protected boolean isValid(AutoAction action, FieldLocation location) {
        return commandMap.containsKey(new AutoEvent(action, location));
    }

    public AutoEventProvider getProvider(){
        return provider;
    }
}
