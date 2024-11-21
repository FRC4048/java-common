package org.usfirst.frc4048.common.loggingv2;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.Logger;

import java.util.*;

public class CommandLogger {
    private final Map<CommandKey, Queue<Boolean>> toLogCommandStatus = new LinkedHashMap<>();
    private static final CommandLogger inst = new CommandLogger();
    private boolean hasInit;

    public static CommandLogger get() {
        return inst;
    }

    private CommandLogger() {}

    public void init() {
        if (hasInit) {
            return;
        }
        CommandScheduler.getInstance().onCommandInitialize(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k-> new LinkedList<>()).add(true);
        });
        CommandScheduler.getInstance().onCommandFinish(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k-> new LinkedList<>()).add(false);
        });
        CommandScheduler.getInstance().onCommandInterrupt(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k-> new LinkedList<>()).add(false);
        });
        hasInit = true;
    }

    public void log() {
        Iterator<Map.Entry<CommandKey, Queue<Boolean>>> iterator = toLogCommandStatus.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<CommandKey, Queue<Boolean>> entry = iterator.next();
            Boolean poll = entry.getValue().poll();
            if (poll != null) {
                Logger.recordOutput("Command/" + entry.getKey().toString(), poll);
            }
            if (entry.getValue().isEmpty()){
                iterator.remove();
            }
        }
    }
}
