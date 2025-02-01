package org.usfirst.frc4048.common.usage.example.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.ElevatorSubsystem;

public class ElevatorPidCommand extends Command {
    private final ElevatorSubsystem subsystem;
    private final double setPoint;
    private double start;

    public ElevatorPidCommand(ElevatorSubsystem subsystem, double setPoint) {
        this.subsystem = subsystem;
        this.setPoint = setPoint;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        start = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        subsystem.setPidGoal(setPoint);
        System.out.println("Command ran with " + setPoint);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
