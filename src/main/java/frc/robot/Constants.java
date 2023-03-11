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
  public static final int kDriverControllerPort = 0;
  public static final int kDriverControllerTwoPort = 1;
  public static final int LEFT_SPARK_ONE_PORT = 3;
  public static final int LEFT_SPARK_TWO_PORT = 4;
  public static final int LEFT_SPARK_THREE_PORT = 10;
  public static final int RIGHT_SPARK_ONE_PORT = 1;
  public static final int RIGHT_SPARK_TWO_PORT = 2;
  public static final int RIGHT_SPARK_THREE_PORT = 9;

  public static final int[] ROLLER_BAR_SOLENOID = {0, 1};
  public static final int LEFT_CLAW_SPARK = 8;
  public static final int RIGHT_CLAW_SPARK = 7;

  
  public static final float UPPER_LIMIT_WRIST = (float) 24.0;
  public static final float LOWER_LIMIT_WRIST = (float) 0.1;
  public static final int[] WRIST_BRAKE_SOLENOID = {2, 3};
  public static final int WRIST_SPARK_PORT = 6;
  public static final double WRIST_MOVEMENT_SPEED = 0.10;
  

  public static final class ElevatorConstant {
    public static final float UPPER_LIMIT_ELEVATOR = 45;
    public static final double LOWER_LIMIT_ELEVATOR = 0.1;
    public static final double ELEVATOR_MOVEMENT_SPEED_UP = 0.2;
    public static final double ELEVATOR_MOVEMENT_SPEED_DOWN = -0.2;
    }
  public static final int ELEVATOR_SPARK = 5;


}

