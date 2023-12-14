package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

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
