// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
  private CANSparkMax wrist;
  private DoubleSolenoid brake;
  public WristSubsystem() {
    wrist = new CANSparkMax(Constants.WRIST_SPARK_PORT, MotorType.kBrushless);
    wrist.setIdleMode(IdleMode.kBrake);
    
    wrist.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.UPPER_LIMIT_WRIST);
    wrist.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.LOWER_LIMIT_WRIST);


    wrist.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, false);
    wrist.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);

    brake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.WRIST_BRAKE_SOLENOID[0], Constants.WRIST_BRAKE_SOLENOID[1]);
  }
  public void upWrist () {
    wrist.set(Constants.WRIST_MOVEMENT_SPEED);
  }

  public void downWrist () {
    wrist.set(-Constants.WRIST_MOVEMENT_SPEED);
  }

  public void stopWrist () {
    wrist.set(0);
  }
  public void brake () {
    brake.set(Value.kForward);
  }
  public void releaseBreak () {
    brake.set(Value.kReverse);
  }

    @Override
  public void periodic() {
    // This method will be called once per schedu
  }
}
