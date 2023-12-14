package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class GenericAutoChooser {
     private final SendableChooser<AutoAction> actionChooser;
     private final SendableChooser<FieldLocation> locationChooser;

     public GenericAutoChooser() {
          actionChooser = new SendableChooser<>();
          locationChooser = new SendableChooser<>();
          Arrays.stream(AutoAction.values()).forEach(a -> actionChooser.addOption(a.getName(), a));
          Arrays.stream(FieldLocation.values()).forEach(l -> locationChooser.addOption(l.name(), l));
          actionChooser.setDefaultOption(getDefaultActionOption().getName(), getDefaultActionOption());
          locationChooser.setDefaultOption(getDefaultLocationOption().name(), getDefaultLocationOption());
     }
     

     public AutoAction getAction() {
          return actionChooser.getSelected() == null ? AutoAction.DoNothing : actionChooser.getSelected();
     }

     public FieldLocation getLocation() {
          return locationChooser.getSelected() == null ? FieldLocation.Middle : locationChooser.getSelected();
     }

     /**
      * This method by default will pick the more specific command over the more generic one.
      * For example if a command had the following criteria: action=TwoPieceMoveLeft, location=any <br>
      * And another command had the criteria: action=TwoPieceMoveLeft, location=left <br>
      * Then the second command would have a higher priority because it is more specific. <br>
      * If two commands the in specificity a random one is chosen
      * @return the corresponding the command of the desired location and action
      */
     public Command getAutoCommand() {
          List<AutoEventComparer> commands = getCommandMap().keySet().stream().filter(event -> event.isAutoEventValid(new AutoEvent(getAction(), getLocation()))).collect(Collectors.toList());
          if (commands.isEmpty())return getDefaultCommand();
          //most specific
          Optional<AutoEventComparer> specificCommand = commands.stream().filter(c -> c.getIncludedActions().size() != AutoAction.values().length && c.getIncludedLocations().size() != FieldLocation.values().length).findFirst();
          if (specificCommand.isPresent()) return getCommandMap().get(specificCommand.get());
          return getCommandMap().get(commands.get(0));
     }

     protected abstract Map<AutoEventComparer, Command> getCommandMap();
     protected abstract CommandBase getDefaultCommand();
     protected abstract AutoAction getDefaultActionOption();
     protected abstract FieldLocation getDefaultLocationOption();

}
