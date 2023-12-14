package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class PrioritizedAutoChooser implements AutoChooser {
    /**
     * This method by default will pick the more specific command over the more generic one.
     * For example if a command had the following criteria: action=TwoPieceMoveLeft, location=any <br>
     * And another command had the criteria: action=TwoPieceMoveLeft, location=left <br>
     * Then the second command would have a higher priority because it is more specific. <br>
     * If two commands the in specificity a random one is chosen
     * @return the corresponding the command of the desired location and action
     */
    public Command getAutoCommand() {
        List<AutoEventComparer> commands = getCommandMap()
                .keySet().stream()
                .filter(event -> event.isAutoEventValid(new AutoEvent(getAutoEventProvider().getSelectedAction(),getAutoEventProvider().getSelectedLocation())))
                .collect(Collectors.toList()
        );
        if (commands.isEmpty()) return getDefaultCommand();
        //most specific
        Optional<AutoEventComparer> specificCommand = commands.stream()
                .filter(c -> c.getIncludedActions().size() != AutoAction.values().length &&
                        c.getIncludedLocations().size() != FieldLocation.values().length)
                .findFirst();
        if (specificCommand.isPresent()) return getCommandMap().get(specificCommand.get());
        return getCommandMap().get(commands.get(0));
    }

}
