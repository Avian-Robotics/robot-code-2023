// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  
  // Neo Controller
  private CANSparkMax elevatorSpark;
  private SparkMaxLimitSwitch upperLimit;
  private SparkMaxLimitSwitch lowerLimit;


  public ElevatorSubsystem() {
    elevatorSpark = new CANSparkMax(Constants.ELEVATOR_SPARK, MotorType.kBrushless);
    elevatorSpark.setSoftLimit(SoftLimitDirection.kForward, Constants.ElevatorConstant.UPPER_LIMIT_ELEVATOR);
    elevatorSpark.setIdleMode(IdleMode.kBrake);

    elevatorSpark.getEncoder().setPosition(Constants.ElevatorConstant.LOWER_LIMIT_ELEVATOR);
  }
  
  public void upElevator () {
    elevatorSpark.set(Constants.ElevatorConstant.ELEVATOR_MOVEMENT_SPEED_UP);
  }

  public void downElevator () {
    elevatorSpark.set(Constants.ElevatorConstant.ELEVATOR_MOVEMENT_SPEED_DOWN);
  }

  public void stopElevator () {
    elevatorSpark.set(0);
  }

  public boolean upperLimit() {
    return upperLimit.isPressed();
  }  

  public boolean lowerLimit() {
    return lowerLimit.isPressed();
  }  

  @Override
  public void periodic() {
  System.out.println(elevatorSpark.getEncoder().getPosition());
/*if (lowerLimit.isPressed() == true){
    elevatorSpark.getEncoder().setPosition(0.0);
    }*/
  }
}
