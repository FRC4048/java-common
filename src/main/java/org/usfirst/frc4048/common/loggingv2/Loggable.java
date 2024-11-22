package org.usfirst.frc4048.common.loggingv2;

import edu.wpi.first.wpilibj2.command.Command;

public interface Loggable {
    String getBasicName();
    void setParent(Command loggable);
}
