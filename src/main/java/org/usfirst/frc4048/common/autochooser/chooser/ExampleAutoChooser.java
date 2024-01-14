package org.usfirst.frc4048.common.autochooser.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auto.*;
import frc.robot.auto.event.AutoEvent;

import java.util.Map;

public class ExampleAutoChooser extends Nt4AutoChooser {
     private final Map<AutoEvent,Command> commandMap = Map.of(
             new AutoEvent(AutoAction.TwoPieceMoveLeft,FieldLocation.Right), new PlaceHolderCommand(),
             new AutoEvent(AutoAction.TwoPieceMoveLeft,FieldLocation.Left), new PlaceHolderCommand()
     );
     @Override
     public Command getAutoCommand() {
          return commandMap.get(new AutoEvent(getAutoEventProvider().getSelectedAction(),getAutoEventProvider().getSelectedLocation()));
     }
}
