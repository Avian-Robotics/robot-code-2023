// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */
  private CANSparkMax leftSparkOne; 
  private CANSparkMax leftSparkTwo;
  private CANSparkMax rightSparkOne;
  private CANSparkMax rightSparkTwo;
  private DifferentialDrive drive; 


  public DrivetrainSubsystem() {
    leftSparkOne = new CANSparkMax(Constants.LEFT_SPARK_ONE_PORT, MotorType.kBrushed);
    leftSparkTwo = new CANSparkMax(Constants.LEFT_SPARK_TWO_PORT, MotorType.kBrushed);
    rightSparkOne = new CANSparkMax(Constants.RIGHT_SPARK_ONE_PORT, MotorType.kBrushed);
    rightSparkTwo = new CANSparkMax(Constants.RIGHT_SPARK_TWO_PORT, MotorType.kBrushed);

    rightSparkOne.setInverted(false);
    rightSparkTwo.setInverted(false);
    leftSparkOne.setInverted(true);
    leftSparkTwo.setInverted(true);

    leftSparkOne.setSmartCurrentLimit(40);
    leftSparkTwo.setSmartCurrentLimit(40);
    rightSparkOne.setSmartCurrentLimit(40);
    rightSparkTwo.setSmartCurrentLimit(40);

    rightSparkOne.burnFlash();
    rightSparkTwo.burnFlash();
    leftSparkOne.burnFlash();
    leftSparkTwo.burnFlash();

    MotorControllerGroup leftSparks = new MotorControllerGroup(leftSparkOne, leftSparkTwo);
    MotorControllerGroup rightSparks = new MotorControllerGroup(rightSparkOne, rightSparkTwo);

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
