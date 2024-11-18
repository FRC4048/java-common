package org.usfirst.frc4048.common.loggingv2;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.Objects;
import java.util.Set;

public class LoggableCommandWrapper extends Command implements Loggable {
    private String basicName = getClass().getSimpleName();
    private Command parent = new BlankCommand();
    private final Command wrap;

    public LoggableCommandWrapper(Command toWrap) {
        this.wrap = toWrap;
    }

    public static LoggableCommandWrapper wrap(Command toWrap) {
        return new LoggableCommandWrapper(toWrap);
    }

    @Override
    public String getBasicName() {
        return basicName;
    }

    @Override
    public String toString() {
        String prefix = parent.toString();
        if (!prefix.isBlank()){
            prefix = prefix.substring(0,prefix.length() - 5);
            prefix += "/";
        }
        return prefix + getBasicName() + "/inst";
    }

    @Override
    public void setParent(Command loggable) {
        this.parent = loggable == null ? new BlankCommand() : loggable;
    }

    public LoggableCommandWrapper withBasicName(String name) {
        basicName = name;
        return this;
    }

    @Override
    public void initialize() {
        wrap.initialize();
    }

    @Override
    public void execute() {
        wrap.execute();
    }

    @Override
    public void end(boolean interrupted) {
        wrap.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return wrap.isFinished();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return wrap.getRequirements();
    }

    @Override
    public InterruptionBehavior getInterruptionBehavior() {
        return wrap.getInterruptionBehavior();
    }

    @Override
    public boolean runsWhenDisabled() {
        return wrap.runsWhenDisabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggableCommandWrapper that = (LoggableCommandWrapper) o;
        return Objects.equals(basicName, that.basicName) && Objects.equals(parent, that.parent) && Objects.equals(wrap, that.wrap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basicName, parent, wrap);
    }
}
