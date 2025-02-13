package org.usfirst.frc4048.common.autochooserv2.event;

import org.littletonrobotics.junction.LogTable;
import org.littletonrobotics.junction.inputs.LoggableInputs;
import org.usfirst.frc4048.common.autochooserv2.AutoAction;
import org.usfirst.frc4048.common.autochooserv2.FieldLocation;

public class AutoChooserInputs implements LoggableInputs {
    AutoAction action = AutoAction.INVALID;
    FieldLocation location = FieldLocation.INVALID;
    AutoAction defaultAction = AutoAction.INVALID;
    FieldLocation defaultLocation = FieldLocation.INVALID;
    AutoAction feedbackAction = AutoAction.INVALID;
    FieldLocation feedbackLocation = FieldLocation.INVALID;

    @Override
    public void toLog(LogTable table) {
        table.put("action", action);
        table.put("location", location);
        table.put("defaultAction", defaultAction);
        table.put("defaultLocation", defaultLocation);
        table.put("feedbackAction", feedbackAction);
        table.put("feedbackLocation", feedbackLocation);
    }

    @Override
    public void fromLog(LogTable table) {
        action = table.get("action", action);
        location = table.get("location", location);
        defaultAction = table.get("defaultAction", defaultAction);
        defaultLocation = table.get("defaultLocation", defaultLocation);
        feedbackAction = table.get("feedbackAction", feedbackAction);
        feedbackLocation = table.get("feedbackLocation", feedbackLocation);
    }
}
