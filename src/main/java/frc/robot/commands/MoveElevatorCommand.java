// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.WristSubsystem;

public class MoveElevatorCommand extends CommandBase {
  /** Creates a new MoveElevator. */
  private final ElevatorSubsystem elevatorSubsystem;
  private final double position;
  public MoveElevatorCommand(ElevatorSubsystem elevatorSubsystem, double position) {
      this.elevatorSubsystem = elevatorSubsystem;
      this.position = position;
      addRequirements(this.elevatorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elevatorSubsystem.setSetpoint(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevatorSubsystem.moveElevatorPid();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevatorSubsystem.atSetpoint();
  }
}
