package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightCamera extends SubsystemBase {
  private final NetworkTable table;
    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees = 17.00;

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 26.375;
  
    // distance from the target to the floor
    double goalHeightInches = 104;
      // private final double cc = -0.1; // Proportional control constant
  // private final double minCommand = 0.05; // Minimum amount to slightly move
  // the robot

  public LimelightCamera() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
  }

  @Override
  public void periodic() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
    SmartDashboard.putNumber("LimelightX", getTargetXOffset());
    SmartDashboard.putNumber("LimelightY", getTargetYOffset());
    SmartDashboard.putNumber("LimelightArea", getTargetArea());

  }

  /**
   * uses the targetXOffset value to calculate the steeringAdjust value.
   */
  // public double getSteeringAdjust() {
  // double steeringAdjust = 0.0;
  // double targetX = getTargetXOffset();

  // steeringAdjust = (targetX > 1.0)
  // ? cc * -1 * targetX - minCommand
  // : cc * -1 * targetX + minCommand;

  // return steeringAdjust;
  // }

  public double getTurretAdjust() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    double tx = table.getEntry("tx").getDouble(0);
    float Kp = 0.1f;
    float min_command = 0.05f;
    float heading_error = (float) -tx;
    float steering_adjust = 0.0f;
    if (tx > 1.0)
    {
            steering_adjust = Kp*heading_error - min_command;
    }
    else if (tx < 1.0)
    {
            steering_adjust = Kp*heading_error + min_command;
    }
    else if (tx == 0){
      steering_adjust = 0;
    }
    return steering_adjust*-1;
    
  }

  public double getHoodAdjust() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    double ty = table.getEntry("ty").getDouble(0);
    double targetOffsetAngle_Vertical = ty;

  double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
  double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

  //calculate distance
  double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);

  return distanceFromLimelightToGoalInches;
  }

  public double getTargetXOffset() {
    return table.getEntry("tx").getDouble(0);
    // returns 0 if no target
  }

  private double getTargetYOffset() {
    return table.getEntry("ty").getDouble(0);
  }

  private double getTargetArea() {
    return table.getEntry("ta").getDouble(0);
  }

}