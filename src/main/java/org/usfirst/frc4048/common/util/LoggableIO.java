package org.usfirst.frc4048.common.util;

import org.littletonrobotics.junction.inputs.LoggableInputs;

public interface LoggableIO<T extends LoggableInputs> {
    void updateInputs(T inputs);
}
