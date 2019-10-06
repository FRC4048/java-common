package org.usfirst.frc4048.common.logging;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.Set;
import java.util.TreeSet;

abstract public class LoggedCommand extends Command {
	private final String ident;
	private final Set<String> requirements = new TreeSet<String>();

	public LoggedCommand(final String ident) {
		this.ident = ident;
	}

	private void log(final String text) {
		final StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" ");
		sb.append(ident);
		// TODO: Cache the string value for requirements to save on creation every time the log message is called
		Logging.instance().traceMessage(Logging.MessageLevel.INFORMATION, sb.toString(), requirements.toString(), text);
	}
	
	/**
	 * Overrides Command.requires() so that LoggedCommand can log which subsystems
	 * are required by the command.
	 */
	@Override
	public synchronized void requires(Subsystem s) {
		super.requires(s);
		requirements.add(s.toString());
	}

	/**
	 * Overrides Command.clearRequirements() so that LoggedCommand can log which
	 * subsystems are required by the command.
	 */
	@Override
	public synchronized void clearRequirements() {
		super.clearRequirements();
		requirements.clear();
	}

	@Override
	final protected boolean isFinished() {
		final boolean result = loggedIsFinished();
		if (result)
			log("isFinished()");
		return result;
	}

	abstract protected boolean loggedIsFinished();

	@Override
	final protected void initialize() {
		log("initialize()");
		loggedInitialize();
	}

	abstract protected void loggedInitialize();

	@Override
	final protected void execute() {
		//log("execute()");
		loggedExecute();
	}

	abstract protected void loggedExecute();

	@Override
	final protected void end() {
		log("end()");
		loggedEnd();
	}

	abstract protected void loggedEnd();

	@Override
	final protected void interrupted() {
		log("interrupted()");
		// TODO: THe behavior of calling super.xx() and then loggedXX() is incorrect in the sense that the super() call cannot
		// be overridden and the loggedXX() has to be implemented. Need to fix this somehow.
		super.interrupted();
		loggedInterrupted();
	}

	/**
	 * Called from Command.interrupted()
	 */
	abstract protected void loggedInterrupted();

	@Override
	final protected synchronized boolean isTimedOut() {
		final boolean result = super.isTimedOut();
		if (result)
			log("isTimedOut()");
		return result;
	}

	@Override
	final public synchronized void cancel() {
		super.cancel();
		loggedCancel();
		if (DriverStation.getInstance().isEnabled()) {
			log("cancel()");
		}
	}

	/**
	 * Called from Command.cancel()
	 */
	abstract protected void loggedCancel();
}