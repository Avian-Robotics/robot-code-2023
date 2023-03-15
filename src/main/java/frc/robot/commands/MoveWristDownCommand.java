package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.WristSubsystem;


public class MoveWristDownCommand extends CommandBase {
    private final WristSubsystem wristSubsystem;

    public MoveWristDownCommand(WristSubsystem wristSubsystem) {
        this.wristSubsystem = wristSubsystem;
        addRequirements(this.wristSubsystem);
    }

    @Override
    public void initialize() {
        if (!isFinished()) {
            wristSubsystem.releaseBreak();
            wristSubsystem.downWrist();
        }
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return wristSubsystem.getWristPos() <= Constants.WristConstant.LOWER_LIMIT_WRIST;
    }

    @Override
    public void end(boolean interrupted) {
        wristSubsystem.stopWrist();
        wristSubsystem.brake();
    }
}
