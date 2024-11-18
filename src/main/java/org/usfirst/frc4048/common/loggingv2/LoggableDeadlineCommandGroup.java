package org.usfirst.frc4048.common.loggingv2;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ProxyCommand;

import java.util.Objects;

public class LoggableDeadlineCommandGroup extends ParallelDeadlineGroup implements Loggable {
    private String basicName = getClass().getSimpleName();
    private Command parent = new BlankCommand();

    @SafeVarargs
    public <T extends Command & Loggable> LoggableDeadlineCommandGroup(T deadline, T... others) {
        super(new Command() {});
        ProxyCommand[] proxyCommands = new ProxyCommand[others.length];
        for (int i = 0; i < others.length; i++) {
            others[i].setParent(this);
            proxyCommands[i] = others[i].asProxy();
        }
        addCommands(proxyCommands);
        deadline.setParent(this);
        setDeadline(deadline.asProxy());
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

    public LoggableDeadlineCommandGroup withBasicName(String name) {
        basicName = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggableDeadlineCommandGroup that = (LoggableDeadlineCommandGroup) o;
        return Objects.equals(basicName, that.basicName) && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basicName, parent);
    }
}
