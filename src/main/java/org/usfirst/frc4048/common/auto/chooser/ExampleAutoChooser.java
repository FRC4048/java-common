package org.usfirst.frc4048.common.auto.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.auto.AutoAction;
import org.usfirst.frc4048.common.auto.FieldLocation;
import org.usfirst.frc4048.common.auto.PlaceHolderCommand;
import org.usfirst.frc4048.common.auto.event.AutoEventComparer;
import org.usfirst.frc4048.common.auto.event.AutoEventProvider;
import org.usfirst.frc4048.common.auto.event.Nt4AutoEventProvider;

import java.util.HashMap;
import java.util.Map;

public class ExampleAutoChooser extends PrioritizedAutoChooser {
     Nt4AutoEventProvider provider = new Nt4AutoEventProvider(AutoAction.DoNothing, FieldLocation.Middle);
     Command defualtCommand = new PlaceHolderCommand();

     @Override
     Map<AutoEventComparer, Command> getCommandMap() {
          Map<AutoEventComparer,Command> map = new HashMap<>();
          map.put(AutoEventComparer.fromAction(AutoAction.DoNothing), new PlaceHolderCommand());
          map.put(AutoEventComparer.fromActionAndLocation(AutoAction.TwoPieceMoveLeft, FieldLocation.Right), new PlaceHolderCommand());
          map.put(AutoEventComparer.fromActionAndLocation(AutoAction.TwoPieceMoveLeft, FieldLocation.Middle), new PlaceHolderCommand());
          map.put(AutoEventComparer.fromAction(AutoAction.TwoPieceMoveLeft), new PlaceHolderCommand());
          return map;
     }

     @Override
     public Command getDefaultCommand() {
          return defualtCommand;
     }

     @Override
     public AutoEventProvider getAutoEventProvider() {
          return provider;
     }

}
