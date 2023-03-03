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
  private CANSparkMax wristSpark;
  private DoubleSolenoid brake;
  public WristSubsystem() {
    wristSpark = new CANSparkMax(Constants.WRIST_SPARK_PORT, MotorType.kBrushless);
    brake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.WRIST_BRAKE_SOLENOID[0], Constants.WRIST_BRAKE_SOLENOID[1]);
    wristSpark.setIdleMode(IdleMode.kBrake);
    wristSpark.setInverted(true);

    wristSpark.getEncoder().setPosition(0.0);
  }

  public double getWristPos() {
      return wristSpark.getEncoder().getPosition();
  }

  public void upWrist () {
    wristSpark.set(Constants.WRIST_MOVEMENT_SPEED);
  }

  public void downWrist () {
    wristSpark.set(-Constants.WRIST_MOVEMENT_SPEED);
  }

  public void stopWrist () {
    wristSpark.set(0);
  }
  public void brake () {
    stopWrist();
    brake.set(Value.kForward);
    wristSpark.setIdleMode(IdleMode.kCoast);
  }
  public void releaseBreak () {
    brake.set(Value.kReverse);
    wristSpark.setIdleMode(IdleMode.kBrake);
  }

    @Override
  public void periodic() {
  }
}
