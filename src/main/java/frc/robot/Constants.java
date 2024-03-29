// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int kDriverControllerPort = 1;
  public static final int kDriverControllerTwoPort = 0;
  public static final int LEFT_SPARK_ONE_PORT = 3;
  public static final int LEFT_SPARK_TWO_PORT = 4;
  public static final int LEFT_SPARK_THREE_PORT = 10;
  public static final int RIGHT_SPARK_ONE_PORT = 1;
  public static final int RIGHT_SPARK_TWO_PORT = 2;
  public static final int RIGHT_SPARK_THREE_PORT = 9;

  public static final double DRIVE_SPEED_COEFFICIENT = 1;

  public static final int[] ROLLER_BAR_SOLENOID = {4, 5};
  public static final int LEFT_CLAW_SPARK = 8;
  public static final int RIGHT_CLAW_SPARK = 7;
  public static final double INTAKE_ROLLER_SPEED = 0.2;
  public static final double OUTAKE_ROLLER_SPEED = 0.37;

  public static final class WristConstant{
    public static final double UPPER_LIMIT_WRIST = 23;
    public static final double LOWER_LIMIT_WRIST = 0.0;
    public static final int[] WRIST_BRAKE_SOLENOID = {2, 3};
    public static final int WRIST_SPARK_PORT = 6;
    public static final double WRIST_MOVEMENT_SPEED = 0.2;
    public static final double P = 0;
    public static final double I = 0;
    public static final double F = 0;
    public static final double D = 0;
    public static final double LEVEL_THREE = 0;
    public static final double LEVEL_TWO = 0;
    public static final double LEVEL_ONE = 0;
  }
  

  public static final class ElevatorConstant {
    public static final float UPPER_LIMIT_ELEVATOR = 37; // TODO: Change back to 43
    public static final double LOWER_LIMIT_ELEVATOR = 0.1;
    public static final double ELEVATOR_MOVEMENT_SPEED_UP = 0.35;
    public static final double ELEVATOR_MOVEMENT_SPEED_DOWN = -0.35;
    public static final double P = 0.1;
    public static final double I = 0;
    public static final double F = 0.05;
    public static final double D = 0;
    public static final double LEVEL_THREE = 30;
    public static final double LEVEL_TWO = 0;
    public static final double LEVEL_ONE = 0;
    }
  public static final int ELEVATOR_SPARK = 5;

  public static final class AutoConstants{
    public static final double PITCH_P = 0;
    public static final double PITCH_I = 0;
    public static final double PITCH_D = 0;
    public static final double PITCH_TOLERANCE = 0;
    public static final double PITCH_SET_POINT = 0;
  }


}

