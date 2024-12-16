// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.usfirst.frc4048.common.loggingv2.CommandLogger;
import org.usfirst.frc4048.common.util.RobotMode;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import java.util.concurrent.atomic.AtomicReference;

public class ExampleAdvantageScopeRobot extends LoggedRobot {

    private static final AtomicReference<RobotMode> mode = new AtomicReference<>(RobotMode.DISABLED);
    private static DriverStation.Alliance alliance = null;
    private static boolean fmsAttached = false;

    public static RobotMode getMode(){
        return mode.get();
    }

    public static DriverStation.Alliance getAlliance(){
        return alliance;
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
        }

    }

    /**
     * Grab alliance color when robot connects to driver station.
     * However, if driver station is not connected to FMS, it will grab the wrong color.
     * This code is here so it will work properly when not in competition.
     * Competition alliance color selection is handled by {@link ExampleAdvantageScopeRobot#updateFmsAlliance()}
     */
    @Override
    public void driverStationConnected() {
        alliance = DriverStation.getAlliance().orElse(null);
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

    @Override
    public void disabledPeriodic() {
        updateFmsAlliance();
    }

    /**
     * Updates the alliance color if the FMS goes from not attached to attached.
     * This code will only work in competition and there is no fms when you connect locally.
     * Non competition alliance information is updated in {@link ExampleAdvantageScopeRobot#driverStationConnected()}
     */
    private void updateFmsAlliance(){
        if (DriverStation.isDSAttached()){
            boolean fms = DriverStation.isFMSAttached();
            if ((fms && !fmsAttached) || alliance == null){
                alliance = DriverStation.getAlliance().orElse(null);
            }
            fmsAttached = fms;
        }
    }
}
