package org.usfirst.frc4048.common.autochooser.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.autochooser.AutoAction;
import org.usfirst.frc4048.common.autochooser.FieldLocation;
import org.usfirst.frc4048.common.autochooser.PlaceHolderCommand;
import org.usfirst.frc4048.common.autochooser.event.AutoEvent;
import org.usfirst.frc4048.common.autochooser.event.Nt4AutoEventProvider;

import java.util.Map;

public class ExampleAutoChooser extends AutoChooser {
     private final Map<AutoEvent,Command> commandMap = Map.of(
             new AutoEvent(AutoAction.TwoPieceMoveLeft, FieldLocation.Right), new PlaceHolderCommand(),
             new AutoEvent(AutoAction.TwoPieceMoveLeft,FieldLocation.Left), new PlaceHolderCommand()
     );

     public ExampleAutoChooser() {
          super(new Nt4AutoEventProvider(AutoAction.DoNothing, FieldLocation.Middle));
     }

     @Override
     public Command getAutoCommand() {
          return commandMap.get(new AutoEvent(getProvider().getSelectedAction(),getProvider().getSelectedLocation()));
     }
}
