package frc.robot.commands;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Shooter;

public class TargetTurretCommand extends CommandBase {

  private final Shooter turret;
  private final LimelightCamera limelight;


  /**
   * command to target the turret.
   */
  public TargetTurretCommand(Shooter turret, LimelightCamera limelight) {
    this.turret = turret;
    this.limelight = limelight;
    addRequirements(turret, limelight);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turret.turnRotations(limelight.getTurretAdjust());
  }

  public void end() {
    turret.turnRotations(0);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
