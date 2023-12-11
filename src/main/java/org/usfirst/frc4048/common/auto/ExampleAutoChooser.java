package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.Map;

public class ExampleAutoChooser extends GenericAutoChooser{
     @Override
     public AutoAction getDefaultOption() {
          return AutoAction.DoNothing;
     }

     @Override
     Map<AutoEventComparer, Command> getCommandMap() {
          return Map.of(AutoEventComparer.fromAction(AutoAction.DoNothing), new PlaceHolderCommand(),
                  AutoEventComparer.fromActionAndLocation(AutoAction.TwoPieceMoveLeft, FieldLocation.Middle), new PlaceHolderCommand());
     }
     
}
