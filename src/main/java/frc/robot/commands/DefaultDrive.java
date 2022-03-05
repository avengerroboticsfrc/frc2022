package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DriveTrain;

import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

public class DefaultDrive extends CommandBase {
  private final DriveTrain drive;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;
  private final BooleanSupplier turn;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public DefaultDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation, BooleanSupplier turn) {
    super();

    this.drive = subsystem;
    this.forward = forward;
    this.rotation = rotation;
    this.turn = turn;
    
    addRequirements(drive);
  }

@Override
  public void execute() {
    drive.curvatureDrive(forward.getAsDouble(), rotation.getAsDouble(), turn.getAsBoolean());
  }
}