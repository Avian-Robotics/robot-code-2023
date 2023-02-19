// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RollerClawSubsystem extends SubsystemBase {

  private DoubleSolenoid occidentalDisciple;
  private CANSparkMax leftTendon;
  private CANSparkMax rightTendon;

  /** Creates a new RollerClawSubsystem. */
  public RollerClawSubsystem() {
    occidentalDisciple = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.ROLLER_BAR_SOLENOID[0], Constants.ROLLER_BAR_SOLENOID[1]);
    leftTendon = new CANSparkMax(Constants.LEFT_CLAW_SPARK, MotorType.kBrushless);
    rightTendon = new CANSparkMax(Constants.RIGHT_CLAW_SPARK, MotorType.kBrushless);
    leftTendon.follow(rightTendon, true);

  }
  public void closeClaw() {
    occidentalDisciple.set(Value.kForward);
  }

  public void openClaw(){
    occidentalDisciple.set(Value.kReverse);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeRotation() {
    rightTendon.set(0.5);
  }

  public void haltRotation() {
    rightTendon.set(0);
  }

  public void outtakeRotation() {
    rightTendon.set(-0.5);
  }
  
  
}
