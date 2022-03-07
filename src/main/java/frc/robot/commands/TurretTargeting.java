package frc.robot.commands;

import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretTargeting extends CommandBase {

  private final Shooter turret;
  private final LimelightCamera limelight;

  public TurretTargeting(Shooter sub, LimelightCamera sub2) {
      turret = sub;
      limelight = sub2;
      addRequirements(turret, limelight);
  }

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
// public class GrabHatch extends CommandBase {
//   // The subsystem the command runs on
//   private final HatchSubsystem m_hatchSubsystem;

//   public GrabHatch(HatchSubsystem subsystem) {
//     m_hatchSubsystem = subsystem;
//     addRequirements(m_hatchSubsystem);
//   }

  @Override
  public void initialize() {  
  System.out.println("init");
    }

    @Override
    public void execute() {
        turret.turnRotations(limelight.getHoodAdjust());
      System.out.println(limelight.getHoodAdjust());
    }

  @Override
  public boolean isFinished() {
    return false;
  }
}
