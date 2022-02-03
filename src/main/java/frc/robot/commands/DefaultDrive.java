package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DriveTrain;

import java.util.function.DoubleSupplier;

public class DefaultDrive extends CommandBase {
  private final DriveTrain drive;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public DefaultDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    super();

    this.drive = subsystem;
    this.forward = forward;
    this.rotation = rotation;

    addRequirements(drive);
  }

  @Override
  public void execute() {
    drive.curvatureDrive(forward.getAsDouble() * .5, rotation.getAsDouble() * .5);
  }
}
