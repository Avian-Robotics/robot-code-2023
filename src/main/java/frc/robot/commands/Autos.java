// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
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

//Drive only, backup, then go forward
  public static Command driveForward(DrivetrainSubsystem drivetrainSubsystem){
    return Commands.runEnd(() -> drivetrainSubsystem.drive(0.50, 0.0),
    () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
    .withTimeout(3)
      .andThen(
         Commands.runEnd(() -> drivetrainSubsystem.drive(-0.4, 0.0),
       () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
       .withTimeout(1.25)
       );
       */
      return Commands.runEnd(() -> drivetrainSubsystem.drive(-0.4, 0.0),
      () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem) 
      .withTimeout(1.25);
  }
  //Will score backwards
  public static Command scoreCube(DrivetrainSubsystem drivetrainSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.35),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new ElevatorDownCommand(elevatorSubsystem)
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(-0.30, 0.0),
        () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
        .withTimeout(2.2))
    );
  }

  //Will only score on lvl 3
  public static Command scoreOnly(WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.8),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::fastRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new ElevatorDownCommand(elevatorSubsystem)
    );
  }

  //Will score lvl 3 and drive out of community
  public static Command scoreForward(DrivetrainSubsystem drivetrainSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.95),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new MoveWristDownCommand(wristSubsystem).withTimeout(0.95),
      new ElevatorDownCommand(elevatorSubsystem)
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0.30, 0.0),
        () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
        .withTimeout(2.2))
    );
  }
  
  //Auto that will score lvl 3 and score lvl 2
  public static Command complexAuto(DrivetrainSubsystem drivetrainSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem, RollerClawSubsystem rollerClawSubsystem){
    return Commands.sequence(
      //score on level 3
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.8),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new MoveWristDownCommand(wristSubsystem).withTimeout(0.8),
      new ElevatorDownCommand(elevatorSubsystem)
      //drive back and turn around
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0.30, 0.0),
        () -> drivetrainSubsystem.drive(0, 0), drivetrainSubsystem)
        .withTimeout(2.2))
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0, 0.35), 
        () -> drivetrainSubsystem.drive(0, 0), drivetrainSubsystem)
        .withTimeout(1.0)),
      //grab cube
      new MoveWristUpCommand(wristSubsystem).withTimeout(1.0),
      new InstantCommand(rollerClawSubsystem::outtakeRotation, rollerClawSubsystem),
      new WaitCommand(1.0),
      new InstantCommand(rollerClawSubsystem::haltRotation, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new MoveWristDownCommand(wristSubsystem).withTimeout(1.0)
      //drive back
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0.30,0.0), 
        () -> drivetrainSubsystem.drive(0,0.0), drivetrainSubsystem)
        .withTimeout(2.2)),
      //score backwards
      new ElevatorUpCommand(elevatorSubsystem),
      new MoveWristUpCommand(wristSubsystem).withTimeout(0.35),
      // new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
      new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new MoveWristDownCommand(wristSubsystem).withTimeout(0.35),
      new ElevatorDownCommand(elevatorSubsystem)
    );
  }
  
  public static Command autoBalance(DrivetrainSubsystem drivetrainSubsystem, RollerClawSubsystem rollerClawSubsystem, WristSubsystem wristSubsystem, ElevatorSubsystem elevatorSubsystem ){
    return Commands.sequence(
    new ElevatorUpCommand(elevatorSubsystem),
    new MoveWristUpCommand(wristSubsystem).withTimeout(1.0),
    new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem),
     new InstantCommand(rollerClawSubsystem::intakeRotation,rollerClawSubsystem),
      new WaitCommand(1.5),
      new InstantCommand(rollerClawSubsystem::haltRotation,rollerClawSubsystem),
      new MoveWristDownCommand(wristSubsystem).withTimeout(1.2),
      new ElevatorDownCommand(elevatorSubsystem)
        .alongWith(Commands.runEnd(() -> drivetrainSubsystem.drive(0.37, 0.0),
        () -> drivetrainSubsystem.drive(0, 0.0), drivetrainSubsystem)
        .withTimeout(2.2)), */
    new AutoBalance(drivetrainSubsystem),
    new WaitCommand(5.0),
    Commands.runOnce(() -> drivetrainSubsystem.setCoastMode(), drivetrainSubsystem)
    );
  }
}

