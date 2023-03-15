// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.sound.sampled.SourceDataLine;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  
  private CANSparkMax elevatorSpark;
  private DigitalInput magSwitch;
  private PIDController pidController;

  public ElevatorSubsystem() {
    elevatorSpark = new CANSparkMax(Constants.ELEVATOR_SPARK, MotorType.kBrushless);
    elevatorSpark.setSoftLimit(SoftLimitDirection.kForward, Constants.ElevatorConstant.UPPER_LIMIT_ELEVATOR);
    elevatorSpark.setIdleMode(IdleMode.kBrake);

    elevatorSpark.setSmartCurrentLimit(35);

    elevatorSpark.getEncoder().setPosition(Constants.ElevatorConstant.LOWER_LIMIT_ELEVATOR);

    magSwitch = new DigitalInput(9);

    pidController = new PIDController(Constants.ElevatorConstant.P, Constants.ElevatorConstant.I, Constants.ElevatorConstant.D);
    pidController.setTolerance(4);
  }

  public void moveElevatorPid() {
    elevatorSpark.set(MathUtil.clamp(pidController.calculate(getElevatorPos()) + Constants.ElevatorConstant.F, -0.3, 0.3));
  }

  public boolean atSetpoint() {
    return pidController.atSetpoint();
  }

  public void setSetpoint(double setpoint) {
    pidController.setSetpoint(setpoint);
  }
  
  public void upElevator () {
    elevatorSpark.set(Constants.ElevatorConstant.ELEVATOR_MOVEMENT_SPEED_UP);
  }

  public void downElevator () {
    elevatorSpark.set(Constants.ElevatorConstant.ELEVATOR_MOVEMENT_SPEED_DOWN);
  }

  public void stopElevator () {
    elevatorSpark.set(0.05);
  }

  public boolean getMagSwitch() {
    return !magSwitch.get();
  }

  public double getElevatorPos() {
    //This can be used to help find the position of the elevator incase the encoder level changes
    return elevatorSpark.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
  if (getMagSwitch()){
    elevatorSpark.getEncoder().setPosition(0.0);
    }
    System.out.println(getElevatorPos());
  }
  
}
