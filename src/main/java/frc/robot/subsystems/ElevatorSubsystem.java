// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
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
    upperLimit = elevatorSpark.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
    lowerLimit = elevatorSpark.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
    elevatorSpark.setIdleMode(IdleMode.kBrake);
  }
  
  public void upElevator () {
    elevatorSpark.set(Constants.ELEVATOR_MOVEMENT_SPEED_UP);
  }

  public void downElevator () {
    elevatorSpark.set(Constants.ELEVATOR_MOVEMENT_SPEED_DOWN);
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
    // This method will be called once per scheduler run
  }
}
