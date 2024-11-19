// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.usfirst.frc4048.common.BuildConstants;
import org.usfirst.frc4048.common.loggingv2.CommandLogger;
import org.usfirst.frc4048.common.util.RobotMode;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public class ExampleAdvantageScopeRobot extends LoggedRobot {
    private static final ConcurrentLinkedQueue<Runnable> runInMainThread = new ConcurrentLinkedQueue<>();

    private static final AtomicReference<RobotMode> mode = new AtomicReference<>(RobotMode.DISABLED);

    public static RobotMode getMode(){
        return mode.get();
    }

    @Override
    public void robotInit() {
        if (Constants.ENABLE_LOGGING) {
            Logger.recordMetadata("ProjectName", "FRC2024_Java"); // Set a metadata value
            Logger.recordMetadata("GitSHA", BuildConstants.GIT_SHA);
            if (isReal()) {
                Logger.addDataReceiver(new WPILOGWriter()); // Log to a USB stick ("/U/logs")
            } else {
                setUseTiming(false); // Run as fast as possible (false == run fast, true == run real)
                String logPath = LogFileUtil.findReplayLog(); // Pull the replay log from AdvantageScope (or prompt the user)
                Logger.setReplaySource(new WPILOGReader(logPath)); // Read replay log
                Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim"))); // Save outputs to a new log
            }
            Logger.start(); // Start logging! No more data receivers, replay sources, or metadata values may be added.
            // Log active commands
            CommandLogger.get().init();
        }
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        if (Constants.ENABLE_LOGGING){
            CommandLogger.get().log();
            long startTime = Logger.getRealTimestamp();
            Runnable poll = runInMainThread.poll();
            while (poll != null){
                poll.run();
                if (Logger.getRealTimestamp() - startTime <= 3000){
                    poll = runInMainThread.poll();
                }else {
                    break;
                }
            }
        }

    }

    @Override
    public void disabledInit() {
        mode.set(RobotMode.DISABLED);
    }

    @Override
    public void autonomousInit() {
        mode.set(RobotMode.AUTONOMOUS);
    }

    @Override
    public void teleopInit() {
        mode.set(RobotMode.TELEOP);
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        mode.set(RobotMode.TEST);
        CommandScheduler.getInstance().cancelAll();
    }


    @Override
    public void simulationInit() {
        mode.set(RobotMode.SIMULATION);
    }

    public static void runInMainThread(Runnable r){
        runInMainThread.add(r);
    }
}
