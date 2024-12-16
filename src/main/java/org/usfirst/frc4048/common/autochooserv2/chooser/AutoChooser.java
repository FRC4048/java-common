package org.usfirst.frc4048.common.autochooserv2.chooser;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import org.usfirst.frc4048.common.autochooserv2.event.AutoEvent;
import org.usfirst.frc4048.common.autochooserv2.event.AutoEventProvider;

/**
 * interface for taking in a {@link AutoEvent} and returning the corresponding {@link Command}
 */
public interface AutoChooser {
    /**
     * @return Command that corresponds to the selected {@link AutoEvent}
     * from the {@link AutoEventProvider}
     */
    Command getAutoCommand();
    Pose2d getStartingPosition();

}
