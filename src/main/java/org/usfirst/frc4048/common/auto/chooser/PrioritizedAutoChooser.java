package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.auto.event.AutoEvent;
import org.usfirst.frc4048.common.auto.event.AutoEventComparer;

import java.util.List;
import java.util.Map;

public abstract class PrioritizedAutoChooser extends CompariableAutoChooser {
    /**
     * This method by default will pick the more specific command over the more generic one.
     * For example if a command had the following criteria: action=TwoPieceMoveLeft, location=any <br>
     * And another command had the criteria: action=TwoPieceMoveLeft, location=left <br>
     * Then the second command would have a higher priority because it is more specific. <br>
     * If two commands the in specificity a random one is chosen
     * @return the corresponding the command of the desired location and action
     */
    public Command getAutoCommand() {
        List<Map.Entry<AutoEventComparer, Command>> list = getCommandMap().entrySet()
                .stream()
                .filter(e -> e.getKey().isAutoEventValid(new AutoEvent(getAutoEventProvider().getSelectedAction(), getAutoEventProvider().getSelectedLocation())))
                .sorted((comp1, comp2) -> {
                    int sum1 = comp1.getKey().getIncludedLocations().size() + comp1.getKey().getIncludedActions().size();
                    int sum2 = comp2.getKey().getIncludedLocations().size() + comp2.getKey().getIncludedActions().size();
                    return Integer.compare(sum1, sum2);
                }).toList();
        return list.isEmpty() ? getDefaultCommand() : list.get(0).getValue();
    }

}
