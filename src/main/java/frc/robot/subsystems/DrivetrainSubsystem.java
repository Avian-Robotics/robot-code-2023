// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */
  private CANSparkMax leftSparkOne; 
  private CANSparkMax leftSparkTwo;
  private CANSparkMax leftSparkThree;
  private CANSparkMax rightSparkOne;
  private CANSparkMax rightSparkTwo;
  private CANSparkMax rightSparkThree;
  private DifferentialDrive drive; 


  public DrivetrainSubsystem() {
    leftSparkOne = new CANSparkMax(Constants.LEFT_SPARK_ONE_PORT, MotorType.kBrushed);
    leftSparkTwo = new CANSparkMax(Constants.LEFT_SPARK_TWO_PORT, MotorType.kBrushed);
    leftSparkThree = new CANSparkMax(Constants.LEFT_SPARK_THREE_PORT, MotorType.kBrushed);
    rightSparkOne = new CANSparkMax(Constants.RIGHT_SPARK_ONE_PORT, MotorType.kBrushed);
    rightSparkTwo = new CANSparkMax(Constants.RIGHT_SPARK_TWO_PORT, MotorType.kBrushed);
    rightSparkThree = new CANSparkMax(Constants.RIGHT_SPARK_THREE_PORT, MotorType.kBrushed);

    rightSparkOne.setInverted(true);
    rightSparkTwo.setInverted(true);
    rightSparkThree.setInverted(false);
    leftSparkOne.setInverted(false);
    leftSparkTwo.setInverted(false);
    leftSparkThree.setInverted(false);

    leftSparkOne.setSmartCurrentLimit(40);
    leftSparkTwo.setSmartCurrentLimit(40);
    leftSparkThree.setSmartCurrentLimit(40);
    rightSparkOne.setSmartCurrentLimit(40);
    rightSparkTwo.setSmartCurrentLimit(40);
    rightSparkThree.setSmartCurrentLimit(40);

    rightSparkOne.burnFlash();
    rightSparkTwo.burnFlash();
    rightSparkThree.burnFlash();
    leftSparkOne.burnFlash();
    leftSparkTwo.burnFlash();
    leftSparkThree.burnFlash();

    MotorControllerGroup leftSparks = new MotorControllerGroup(leftSparkOne, leftSparkTwo, leftSparkThree);
    MotorControllerGroup rightSparks = new MotorControllerGroup(rightSparkOne, rightSparkTwo, rightSparkThree);

    drive = new DifferentialDrive(leftSparks, rightSparks);



  }

  public void drive(double leftPower, double rightPower){
    drive.curvatureDrive(leftPower, rightPower,true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
