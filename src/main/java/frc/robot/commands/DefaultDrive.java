package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DriveTrain;
import java.lang.Math;
import java.util.function.DoubleSupplier;
import java.util.function.BooleanSupplier;

public class DefaultDrive extends CommandBase {
  private final DriveTrain drive;
  private final DoubleSupplier forward;
  private final DoubleSupplier rotation;
  private final BooleanSupplier turn;
  private final DoubleSupplier reverse;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward   The control input for driving forwards/backwards
   * @param rotation  The control input for turning
   */
  public DefaultDrive(DriveTrain subsystem, DoubleSupplier reverse, DoubleSupplier forward, DoubleSupplier rotation, BooleanSupplier turn) {
    super();
    this.drive = subsystem;
    this.reverse = reverse;
    this.forward = forward;
    this.rotation = rotation;
    this.turn = turn;

    
    addRequirements(drive);
  }

// @Override
//   public void execute() {
//     double speed = (reverse.getAsDouble() + (forward.getAsDouble()*-1)) * .5;
//     double val = turn.getAsBoolean() ? .35 : 1;
//     double speed2 = speed>0 ? Math.pow(speed, 2) : -Math.pow(speed, 2);
//     double val2 = Math.pow(val, 3);
//     drive.curvatureDrive((speed2 * .5), (rotation.getAsDouble()*val2), turn.getAsBoolean());

//   }

  @Override
  public void execute() {
    double speed = (reverse.getAsDouble() + (forward.getAsDouble()*-1)) * .5;
    double rotate = rotation.getAsDouble();
    double val = turn.getAsBoolean() ? .35 : 1;
    double speed2 = speed>0 ? Math.pow(speed, 2) : -Math.pow(speed, 2);
    double val2 = Math.pow(rotate, 3);
    drive.curvatureDrive((speed2 * .5), (val2*val), turn.getAsBoolean());

  }



  public void forwardAuto() {
    drive.tankDrive(.2, .2);
  }
}