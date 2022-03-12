package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive.DriveTrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class TankDrive extends CommandBase {
  private final DriveTrain drive;
  private final DoubleSupplier left;
  private final DoubleSupplier right;
  private final BooleanSupplier boost;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param left   The control input for driving lefts/backwards
   * @param right  The control input for turning
   */ 
  public TankDrive(DriveTrain subsystem, DoubleSupplier left, DoubleSupplier right, BooleanSupplier boost) {
    super();

    this.drive = subsystem;
    this.left = left;
    this.right = right;
    this.boost = boost;
    
    addRequirements(drive);
  }

  @Override
  public void execute() {

    //adjust depending on Gage's preference
    //This is a tankdrive setup for him for drive test.
    //May want a turbo button, add Double supplier and edit command to get value from trigger
    //Follow DEfault drive bc I figured it out there

    drive.tankDrive(boost.getAsBoolean() ? left.getAsDouble() : left.getAsDouble() * .6, boost.getAsBoolean() ? right.getAsDouble() : right.getAsDouble() * .6);
  }
}
