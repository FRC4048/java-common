// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.usfirst.frc4048.common.usage.example.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ElevatorPidCommand;
import frc.robot.commands.ElevatorRunCommand;
import frc.robot.commands.MotorRunCommand;
import frc.robot.subsystems.elevator.ElevatorSubsystem;
import frc.robot.subsystems.motor.MotorSubsystem;

/** This is a sample program to demonstrate the use of elevator simulation. */
public class Robot extends TimedRobot {
//  private final Joystick m_joystick = new Joystick(Constants.kJoystickPort);
  private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
  private final MotorSubsystem motorSubsystem = new MotorSubsystem();

  public Robot() {}

  @Override
  public void robotInit() {
    SmartDashboard.putData("Elevator up", new ElevatorPidCommand(m_elevator, 2));
    SmartDashboard.putData("Elevator down", new ElevatorPidCommand(m_elevator, 0));
    SmartDashboard.putData("Elevator run", new ElevatorRunCommand(m_elevator, 0.75));
    SmartDashboard.putData("Motor run", new MotorRunCommand(motorSubsystem, 0.75));
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void simulationPeriodic() {
    // Update the simulation model.
//    m_elevator.simulationPeriodic();
  }

  @Override
  public void teleopPeriodic() {
//    if (m_joystick.getTrigger()) {
//      // Here, we set the constant setpoint of 0.75 meters.
//      m_elevator.reachGoal(Constants.kSetpointMeters);
//      SmartDashboard.putBoolean("Trigger", true);
//    } else {
//      // Otherwise, we update the setpoint to 0.
//      m_elevator.reachGoal(0.0);
//      SmartDashboard.putBoolean("Trigger", false);
//    }
  }

  @Override
  public void disabledInit() {
    // This just makes sure that our simulation code knows that the motor's off.
//    m_elevator.stop();
  }
}
