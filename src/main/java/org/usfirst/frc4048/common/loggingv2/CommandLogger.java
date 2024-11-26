package org.usfirst.frc4048.common.loggingv2;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Unit;
import edu.wpi.first.util.WPISerializable;
import edu.wpi.first.util.protobuf.Protobuf;
import edu.wpi.first.util.struct.Struct;
import edu.wpi.first.util.struct.StructSerializable;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.Logger;
import org.usfirst.frc4048.common.Constants;
import us.hebi.quickbuf.ProtoMessage;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommandLogger {
    private final Map<CommandKey, Queue<Boolean>> toLogCommandStatus = new LinkedHashMap<>();
    private final ConcurrentLinkedQueue<Runnable> logsToMainThread = new ConcurrentLinkedQueue<>();
    private static final CommandLogger inst = new CommandLogger();
    private boolean hasInit;

    public static CommandLogger get() {
        return inst;
    }

    private CommandLogger() {
    }

    public void init() {
        if (hasInit) {
            return;
        }
        CommandScheduler.getInstance().onCommandInitialize(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k -> new LinkedList<>()).add(true);
        });
        CommandScheduler.getInstance().onCommandFinish(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k -> new LinkedList<>()).add(false);
        });
        CommandScheduler.getInstance().onCommandInterrupt(command -> {
            toLogCommandStatus.computeIfAbsent(new CommandKey(command.toString()), k -> new LinkedList<>()).add(false);
        });
        hasInit = true;
    }
    public void log(){
        logCommands();
        logQueuedMessages();
    }

    private void logQueuedMessages() {
        long startTime = Logger.getRealTimestamp();
        Runnable poll = logsToMainThread.poll();
        while (poll != null){
            poll.run();
            long now = Logger.getRealTimestamp();
            if (now - startTime <= Constants.MAX_LOG_TIME_WAIT){
                poll = logsToMainThread.poll();
            } else {
                Logger.recordOutput("LOGGING_ERROR", "Logging took too long @" + now);
                break;
            }
        }
    }

    public void logCommands() {
        Iterator<Map.Entry<CommandKey, Queue<Boolean>>> iterator = toLogCommandStatus.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<CommandKey, Queue<Boolean>> entry = iterator.next();
            Boolean poll = entry.getValue().poll();
            if (poll != null) {
                Logger.recordOutput("Command/" + entry.getKey().toString(), poll);
            }
            if (entry.getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }

    public void logMessage(String key, byte[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, boolean value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, int value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, long value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, float value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, double value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, String value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public <E extends Enum<E>> void logMessage(String key, E value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public <U extends Unit<U>> void logMessage(String key, Measure<U> value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, boolean[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, int[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, long[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, float[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, double[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, String[] value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public <T> void logMessage(String key, Struct<T> struct, T value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, struct, value));
    }

    @SafeVarargs
    public final <T> void logMessage(String key, Struct<T> struct, T... value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, struct, value));
    }

    public <T, MessageType extends ProtoMessage<?>> void logMessage(String key, Protobuf<T, MessageType> proto, T value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, proto, value));
    }

    public <T extends WPISerializable> void logMessage(String key, T value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    @SafeVarargs
    public final <T extends StructSerializable> void logMessage(String key, T... value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }

    public void logMessage(String key, Mechanism2d value) {
        logsToMainThread.add(() -> Logger.recordOutput(key, value));
    }
}
