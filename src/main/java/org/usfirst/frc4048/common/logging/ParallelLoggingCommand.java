package org.usfirst.frc4048.common.logging;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import java.util.Arrays;
import java.util.List;

public class ParallelLoggingCommand extends LoggingCommand {
    private static final String THIS_NAME = "-this";

    private List<LoggingCommand> loggingCommands;

    /**
     * Constructor for parallel command group.
     *
     * @param namePrefix the name for the group - this is where the sub-commands for this group will be nested in
     * @param commands   the sub commands for this group (either regular commands or LoggingCommand are OK)
     */
    public ParallelLoggingCommand(String namePrefix, Command... commands) {
        super(namePrefix, THIS_NAME, new ParallelCommandGroup());
        LoggingCommand[] wrapped = CommandUtil.wrapForLogging(namePrefix, commands);
        ((ParallelCommandGroup) getUnderlying()).addCommands(wrapped);
        this.loggingCommands = Arrays.asList(wrapped);
    }

    public final void addCommands(Command... commands) {
        LoggingCommand[] wrapped = CommandUtil.wrapForLogging(getNamePrefix(), commands);
        ((ParallelCommandGroup) getUnderlying()).addCommands(wrapped);
        loggingCommands.addAll(Arrays.asList(wrapped));
    }

    @Override
    public void setName(String name) {
        // Do not change the logging name for this command (it is fixed)
        getUnderlying().setName(name);
    }

    @Override
    public void setNamePrefix(String prefix) {
        super.setNamePrefix(prefix);
        setChildrenPrefix(prefix);
    }

    @Override
    public String toString() {
        return getFullyQualifiedName();
    }

    private void setChildrenPrefix(String prefix) {
        // Recursively change the prefix for all child commands
        for (LoggingCommand loggingCommand : loggingCommands) {
            loggingCommand.setNamePrefix(prefix);
        }
    }
}
