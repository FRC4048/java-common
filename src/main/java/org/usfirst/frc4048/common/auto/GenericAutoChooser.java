package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import jdk.javadoc.doclet.Taglet;
import org.usfirst.frc4048.common.PlaceHolderCommand;

import javax.swing.*;
import java.util.*;

public abstract class GenericAutoChooser implements AutoChooser {
     private SendableChooser<AutoAction> actionChooser;
     private SendableChooser<FieldLocation> locationChooser;

     public GenericAutoChooser() {
          actionChooser = new SendableChooser<>();
          locationChooser = new SendableChooser<>();
     }

     public void init() {
          setOptions();
          actionChooser.setDefaultOption(getDefaultOption().getName(), getDefaultOption());
     }

     @Override
     public void setOptions() {
          Arrays.stream(AutoAction.values()).forEach(a -> actionChooser.addOption(a.getName(), a));
          Arrays.stream(FieldLocation.values()).forEach(l -> locationChooser.addOption(l.name(), l));
     }

     public AutoAction getAction() {
          return actionChooser.getSelected() == null ? AutoAction.DoNothing : actionChooser.getSelected();
     }

     public FieldLocation getLocation() {
          return locationChooser.getSelected() == null ? FieldLocation.Middle : locationChooser.getSelected();
     }

     @Override
     public Command getAutoCommand() {
          Optional<AutoEventComparator> first = getCommandMap().keySet().stream().filter(event -> event.isAutoEventValid(new AutoEvent(getAction(), getLocation()))).findFirst();
          if (first.isPresent()) return getCommandMap().get(first.get());
          else return new PlaceHolderCommand(); //default command
     }

     abstract Map<AutoEventComparator, Command> getCommandMap();

}
