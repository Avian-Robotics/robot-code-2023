// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public final class Autos {
  /** Example static factory for an autonomous command. */

  public static CommandBase driveForward(DrivetrainSubsystem drivetrainSubsystem){
    return Commands.runEnd(() -> drivetrainSubsystem.drive(0.85, 0.0),
    () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
    .withTimeout(3)
    .andThen(
      Commands.runEnd(() -> drivetrainSubsystem.drive(-0.55, 0.0),
    () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
    .withTimeout(2)
    );
  }
}
