package org.usfirst.frc4048.common.usage.example.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class ElevatorRunCommand extends Command {
    private final ElevatorSubsystem subsystem;
    private final double power;
    private double start;

    public ElevatorRunCommand(ElevatorSubsystem subsystem, double power) {
        this.subsystem = subsystem;
        this.power = power;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        start = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        subsystem.setMotorSpeed(power);
        System.out.println("Command ran with " + power);
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setMotorSpeed(0.0);
        System.out.println("Command ran with " + power);
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-start) > 5;
    }
}
