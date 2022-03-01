package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ToggleIntakeCommand extends CommandBase {
  private Intake intake;
  private static boolean isOn = false;

  /**
   * sets up the intake with an intake subsystem.
   */
  public ToggleIntakeCommand(Intake intake) {
    super();

    this.intake = intake;

    addRequirements(intake);
  }

  @Override
  public void execute() {
    if (isOn) {
      intake.setPower(0.5);
    } else {
      intake.setPower(0);
    }

    isOn = !isOn;
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
