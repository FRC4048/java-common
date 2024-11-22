package org.usfirst.frc4048.common.autochooserv2.event;

import org.usfirst.frc4048.common.autochooserv2.AutoAction;
import org.usfirst.frc4048.common.autochooserv2.FieldLocation;

import java.util.Objects;

/**
 * Wrapper Class, that Contains a {@link AutoAction} and a {@link org.usfirst.frc4048.common.autochooserv2.FieldLocation}
 */
public class AutoEvent {
     private final AutoAction action;
     private final FieldLocation location;

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
          return action.equals(autoEvent.action) && location.equals(autoEvent.location);
     }

     @Override
     public int hashCode() {
          return Objects.hash(action, location);
     }
}
