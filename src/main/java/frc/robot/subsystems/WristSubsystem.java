// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase {
  private CANSparkMax wrist;
  
  public WristSubsystem() {
    wrist = new CANSparkMax(Constants.WRIST_SPARK_PORT, MotorType.kBrushless);
    // TODO: Make encoder
    wrist.setIdleMode(IdleMode.kBrake);
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

    @Override
  public void periodic() {
    // This method will be called once per schedu
  }
}
