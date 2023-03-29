package frc.robot.commands;

import java.sql.Driver;
import java.sql.Time;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoBalance extends CommandBase{
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final Timer timer = new Timer();

    public AutoBalance(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;

        timer.start();
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
    }

    @Override
    public void execute() {
        if (drivetrainSubsystem.getPitch() < -6.0){
            drivetrainSubsystem.drive(-0.2, 0);
        }
        else if (drivetrainSubsystem.getPitch() > 6.0){
            drivetrainSubsystem.drive(0.2, 0);
        }
        else {
            drivetrainSubsystem.drive(0, 0);
        }
    }

    @Override
    public boolean isFinished() {
        if (timer.hasElapsed(0.5)){
            timer.reset();
            return Math.abs(drivetrainSubsystem.getPitch()) < 1.0; 
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.drive(0, 0);
    }
}
