package org.usfirst.frc4048.common.auto;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.PlaceHolderCommand;

import java.util.List;
import java.util.Map;

public class ExampleAutoChooser extends GenericAutoChooser{
     @Override
     public AutoAction getDefaultOption() {
          return AutoAction.DoNothing;
     }

     @Override
     Map<AutoEventComparator, Command> getCommandMap() {
          return Map.of(AutoEventComparator.fromActions(List.of(AutoAction.DoNothing)), new PlaceHolderCommand(),
                  AutoEventComparator.fromActionsAndLocations(List.of(AutoAction.TwoPieceMoveLeft), List.of(FieldLocation.Middle)), new PlaceHolderCommand());
     }
     
}
