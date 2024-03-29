package frc.robot.commands;

import java.sql.Driver;
import java.sql.Time;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoBalance extends Command{
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final Timer timer = new Timer();

    public AutoBalance(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;

        timer.start();
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        drivetrainSubsystem.setBrakeMode();
        timer.reset();
    }

    @Override
    public void execute() {
        if (drivetrainSubsystem.getPitch() < -4.0){
            //shouldn't this number be positive bc negative makes it drive forward and positive makes it drive backwards
            drivetrainSubsystem.drive(-0.25, 0);
        }
        else if (drivetrainSubsystem.getPitch() > 4.0){
            //same here should be negative for left power instead of positive
            drivetrainSubsystem.drive(0.25, 0);
        }
        else {
            drivetrainSubsystem.drive(0, 0);
        }
    }

    @Override
    public boolean isFinished() {
        //this may be making it stutter bc of the condition?
        if (timer.hasElapsed(0.5)){
            timer.reset();
            //changed to 1.4 instead of 1
            return Math.abs(drivetrainSubsystem.getPitch()) < 2.0; 
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.drive(0, 0);

        //drivetrainSubsystem.setCoastMode();
    }
}
