package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

import javax.swing.*;
import java.util.*;

public abstract class GenericAutoChooser {
     private SendableChooser<AutoAction> actionChooser;
     private SendableChooser<FieldLocation> locationChooser;

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
     
     public Command getAutoCommand() {
          Optional<AutoEventComparer> first = getCommandMap().keySet().stream().filter(event -> event.isAutoEventValid(new AutoEvent(getAction(), getLocation()))).findFirst();
          if (first.isPresent()) return getCommandMap().get(first.get());
          else return getDefaultCommand();
     }

     abstract Map<AutoEventComparer, Command> getCommandMap();
     abstract CommandBase getDefaultCommand();
     abstract AutoAction getDefaultActionOption();
     abstract FieldLocation getDefaultLocationOption();

}
