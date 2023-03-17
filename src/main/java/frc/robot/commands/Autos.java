// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.RollerClawSubsystem;
import frc.robot.subsystems.WristSubsystem;

public final class Autos {
  /** Example static factory for an autonomous command. */

  public static CommandBase driveForward(DrivetrainSubsystem drivetrainSubsystem){
    return Commands.runEnd(() -> drivetrainSubsystem.drive(0.50, 0.0),
    () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
    .withTimeout(3)
    .andThen(
      Commands.runEnd(() -> drivetrainSubsystem.drive(-0.50, 0.0),
    () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
    .withTimeout(2)
    );
  }
  public static CommandBase scoreCube(DrivetrainSubsystem drivetrainSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.35),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::fastRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new ElevatorDownCommand(elevatorSubsystem)
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(-0.30, 0.0),
        () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
        .withTimeout(2.2))
    );
  }

  public static CommandBase scoreForward(DrivetrainSubsystem drivetrainSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.6),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new ElevatorDownCommand(elevatorSubsystem)
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0.30, 0.0),
        () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
        .withTimeout(2.2))
    );
  }
}
