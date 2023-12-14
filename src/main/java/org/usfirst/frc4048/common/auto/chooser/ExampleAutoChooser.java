package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import org.usfirst.frc4048.common.auto.AutoAction;
import org.usfirst.frc4048.common.auto.FieldLocation;
import org.usfirst.frc4048.common.auto.PlaceHolderCommand;
import org.usfirst.frc4048.common.auto.event.AutoEventComparer;
import org.usfirst.frc4048.common.auto.event.AutoEventProvider;

import java.util.Map;

public class ExampleAutoChooser extends PrioritizedAutoChooser {

     @Override
     public Map<AutoEventComparer, Command> getCommandMap() {
          return Map.of(
                  AutoEventComparer.fromAction(AutoAction.DoNothing), new PlaceHolderCommand(),
                  AutoEventComparer.fromActionAndLocation(AutoAction.TwoPieceMoveLeft, FieldLocation.Middle), new PlaceHolderCommand()
          );
     }

     @Override
     public CommandBase getDefaultCommand() {
          return new PlaceHolderCommand();
     }

     @Override
     public AutoEventProvider getAutoEventProvider() {
          return null;
     }

}
