// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new DrivetrainSubsystem. */
  private CANSparkMax leftSparkOne; 
  private CANSparkMax leftSparkTwo;
  private CANSparkMax leftSparkThree;
  private CANSparkMax rightSparkOne;
  private CANSparkMax rightSparkTwo;
  private CANSparkMax rightSparkThree;
  private DifferentialDrive drive; 
  private AHRS gyro;  


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

    setCoastMode();

    rightSparkOne.burnFlash();
    rightSparkTwo.burnFlash();
    rightSparkThree.burnFlash();
    leftSparkOne.burnFlash();
    leftSparkTwo.burnFlash();
    leftSparkThree.burnFlash();

    leftSparkTwo.follow(leftSparkOne);
    leftSparkThree.follow(leftSparkOne);
    rightSparkTwo.follow(rightSparkOne);
    rightSparkThree.follow(rightSparkOne);


    drive = new DifferentialDrive(leftSparkOne, rightSparkOne);

    gyro = new AHRS(Port.kUSB);
  }

  public void drive(double leftPower, double rightPower){
    drive.curvatureDrive(leftPower, rightPower,true);
  }

  public void setCoastMode() {
    leftSparkOne.setIdleMode(IdleMode.kCoast);
    leftSparkTwo.setIdleMode(IdleMode.kCoast);
    leftSparkThree.setIdleMode(IdleMode.kCoast);
    rightSparkOne.setIdleMode(IdleMode.kCoast);
    rightSparkTwo.setIdleMode(IdleMode.kCoast);
    rightSparkThree.setIdleMode(IdleMode.kCoast);
  }

  public void setBrakeMode() {
    leftSparkOne.setIdleMode(IdleMode.kBrake);
    leftSparkTwo.setIdleMode(IdleMode.kBrake);
    leftSparkThree.setIdleMode(IdleMode.kBrake);
    rightSparkOne.setIdleMode(IdleMode.kBrake);
    rightSparkTwo.setIdleMode(IdleMode.kBrake);
    rightSparkThree.setIdleMode(IdleMode.kBrake);
  }

  public double getPitch() {
    return gyro.getRoll() - 2.6;
}


  @Override
  public void periodic() {
  }
}
