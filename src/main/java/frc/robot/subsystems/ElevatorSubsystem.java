// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  
  // Neo Controller
  private CANSparkMax elevatorSpark;
  private DigitalInput magSwitch;


  public ElevatorSubsystem() {
    elevatorSpark = new CANSparkMax(Constants.ELEVATOR_SPARK, MotorType.kBrushless);
    elevatorSpark.setSoftLimit(SoftLimitDirection.kForward, Constants.ElevatorConstant.UPPER_LIMIT_ELEVATOR);
    elevatorSpark.setIdleMode(IdleMode.kBrake);
    elevatorSpark.setSoftLimit(SoftLimitDirection.kReverse, (float) 0.1);
    elevatorSpark.enableSoftLimit(SoftLimitDirection.kForward, true);
    elevatorSpark.enableSoftLimit(SoftLimitDirection.kReverse, true);

    elevatorSpark.getEncoder().setPosition(Constants.ElevatorConstant.LOWER_LIMIT_ELEVATOR);

    magSwitch = new DigitalInput(9);
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

  public boolean getMagSwitch() {
    return !magSwitch.get();
  }

  @Override
  public void periodic() {
  if (getMagSwitch()){
    elevatorSpark.getEncoder().setPosition(0.0);
    }
  }
}
