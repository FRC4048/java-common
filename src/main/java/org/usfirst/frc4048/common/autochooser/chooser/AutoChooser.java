package org.usfirst.frc4048.common.autochooser.chooser;

import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.autochooser.event.AutoEventProvider;

public abstract class AutoChooser {
     abstract Command getAutoCommand();

     private final AutoEventProvider provider;
     public AutoChooser(AutoEventProvider provider) {
          this.provider = provider;
     }

     public AutoEventProvider getProvider() {
          return provider;
     }
}
