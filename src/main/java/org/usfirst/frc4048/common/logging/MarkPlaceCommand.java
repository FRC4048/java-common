package org.usfirst.frc4048.common.logging;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command can be used (e.g. assigned to a button) to mark a place in the log.
 * When the drive team finds an issue during competition, pressing the button will place a special string in the log
 * and will allow the pit crew to look for the place in the logs to see what went wrong
 */
public class MarkPlaceCommand extends Command {

    public MarkPlaceCommand() {
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, "~~~DriveTeam Breakpoint~~~");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
       
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
        end();
	}
}