package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ZeroEncoderCommand extends CommandBase {
  private final Shooter shooter;

  private final double turnPower = 0.5;

  private static boolean isZeroed = false;
  
  /**
   * creates a new ZeroEncoderCommand.
   * @param shooter the shooter subsystem
   */
  public ZeroEncoderCommand(Shooter shooter) {
    super();

    this.shooter = shooter;

    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.turn(-1 * turnPower);
  }

  @Override
  public void execute() {
    if (shooter.getTurnPosition() < 0) {
      shooter.turn(-1 * turnPower);
    } else {
      shooter.turnRotations(1);
      isZeroed = true;
    }
  }

  @Override
  public boolean isFinished() {
    return isZeroed;
  }
}
