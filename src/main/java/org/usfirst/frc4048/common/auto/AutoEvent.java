package org.usfirst.frc4048.common.auto;

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
}
