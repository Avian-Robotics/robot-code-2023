package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.WristSubsystem;


public class MoveWristUpCommand extends Command {
    private final WristSubsystem wristSubsystem;

    public MoveWristUpCommand(WristSubsystem wristSubsystem) {
        this.wristSubsystem = wristSubsystem;
        addRequirements(this.wristSubsystem);
    }

    @Override
    public void initialize() {
        if (!isFinished()) {
            wristSubsystem.releaseBreak();
            wristSubsystem.upWrist();
        }
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return wristSubsystem.getWristPos() >= Constants.WristConstant.UPPER_LIMIT_WRIST;
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
        wristSubsystem.brake();
    }
}
