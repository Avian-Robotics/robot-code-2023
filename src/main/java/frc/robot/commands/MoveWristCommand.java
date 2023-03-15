// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class MoveWristCommand extends CommandBase {
  /** Creates a new MoveWristCommand. */
  private final WristSubsystem wristSubsystem;
  private final double mainPosition;
  public MoveWristCommand(WristSubsystem wristSubsystem, double position) {
    this.wristSubsystem = wristSubsystem;
    addRequirements(this.wristSubsystem);

    mainPosition = position;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    wristSubsystem.setSetpoint(mainPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    wristSubsystem.moveWristPid();}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return wristSubsystem.atSetpoint();
  }
}
