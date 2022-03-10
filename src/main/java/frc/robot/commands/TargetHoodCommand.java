package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Shooter;

public class TargetHoodCommand extends CommandBase {

  private final Shooter shooter;
  private final LimelightCamera limelight;

  /**
   * command to target the turret.
   */
  public TargetHoodCommand(Shooter shooter, LimelightCamera limelight) {
    this.shooter = shooter;
    this.limelight = limelight;
    addRequirements(shooter, limelight);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.adjustAngle(limelight.getTurretAdjust());
    System.out.println("bruh");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
