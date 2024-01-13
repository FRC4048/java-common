package frc.robot.auto.event;


import frc.robot.auto.AutoAction;
import frc.robot.auto.FieldLocation;

import java.util.Objects;

public class AutoEvent {
     private final AutoAction action;
     private final FieldLocation location;
     ;

     public AutoEvent(AutoAction action, FieldLocation location) {
          this.action = action;
          this.location = location;
     }

     public AutoAction getAction() {
          return action;
     }

     public FieldLocation getLocation() {
          return location;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          AutoEvent autoEvent = (AutoEvent) o;
          return action == autoEvent.action && location == autoEvent.location;
     }

     @Override
     public int hashCode() {
          return Objects.hash(action, location);
     }
}
