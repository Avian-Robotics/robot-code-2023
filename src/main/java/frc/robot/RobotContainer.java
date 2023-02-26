// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.ElevatorDownCommand;
import frc.robot.commands.ElevatorUpCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.RollerClawSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private DrivetrainSubsystem drivetrainSubsystem;
  private RollerClawSubsystem rollerClawSubsystem;
  private WristSubsystem wristSubsystem;
  private ElevatorSubsystem elevatorSubsystem;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController driverController =
      new XboxController(Constants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    drivetrainSubsystem = new DrivetrainSubsystem();
    rollerClawSubsystem = new RollerClawSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> drivetrainSubsystem.drive(driverController.getRawAxis(1), driverController.getRawAxis(2)), drivetrainSubsystem));
    wristSubsystem = new WristSubsystem();
    
    configureBindings();

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    new JoystickButton(driverController, 8).whileTrue(new StartEndCommand(rollerClawSubsystem::intakeRotation, rollerClawSubsystem::haltRotation, rollerClawSubsystem));

    new JoystickButton(driverController, 7).whileTrue(new StartEndCommand(rollerClawSubsystem::outtakeRotation,  rollerClawSubsystem::haltRotation, rollerClawSubsystem));
  
    new JoystickButton(driverController, 5).onTrue(new InstantCommand(rollerClawSubsystem::closeClaw, rollerClawSubsystem));

    new JoystickButton(driverController, 6).onTrue(new InstantCommand(rollerClawSubsystem::openClaw, rollerClawSubsystem));

    new JoystickButton(driverController, 1).whileTrue(new StartEndCommand(() -> {
      wristSubsystem.releaseBreak();
      wristSubsystem.upWrist();
    }, () -> {
      wristSubsystem.stopWrist();
      wristSubsystem.brake();
    }, wristSubsystem)); 
    
    new JoystickButton(driverController, 3).whileTrue(new StartEndCommand(() -> {
      wristSubsystem.releaseBreak();
      wristSubsystem.downWrist();
    }, ()-> {
        wristSubsystem.stopWrist();
        wristSubsystem.brake();
      }, wristSubsystem));

    new JoystickButton(driverController, 4).whileTrue(new ElevatorUpCommand(elevatorSubsystem));
    
    new JoystickButton(driverController, 2).whileTrue(new ElevatorDownCommand(elevatorSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
