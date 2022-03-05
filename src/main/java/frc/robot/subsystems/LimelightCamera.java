package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightCamera extends SubsystemBase {
  private final NetworkTable table;
  private final double cc = -0.1; // Proportional control constant
  private final double minCommand = 0.05; // Minimum amount to slightly move the robot

  public LimelightCamera() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LimelightX", getTargetXOffset());
    SmartDashboard.putNumber("LimelightY", getTargetYOffset());
    SmartDashboard.putNumber("LimelightArea", getTargetArea());
  }

  /**
   * uses the targetXOffset value to calculate the steeringAdjust value.
   */
  // public double getSteeringAdjust() {
  //   double steeringAdjust = 0.0;
  //   double targetX = getTargetXOffset();

  //   steeringAdjust = (targetX > 1.0)
  //       ? cc * -1 * targetX - minCommand
  //       : cc * -1 * targetX + minCommand;

  //   return steeringAdjust;
  // }

  public double getTargetXOffset() {
    return table.getEntry("tx").getDouble(-1);
    //returns -1 if no target
  }

  private double getTargetYOffset() {
    return table.getEntry("ty").getDouble(-1);
  }

  private double getTargetArea() {
    return table.getEntry("ta").getDouble(-1);
  }
}