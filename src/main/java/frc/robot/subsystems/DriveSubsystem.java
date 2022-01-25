package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  private final WPI_VictorSPX[] leftMotors = {
    new WPI_VictorSPX(Constants.leftMotorPorts[0]),
    new WPI_VictorSPX(Constants.leftMotorPorts[1])
  };
  private final WPI_VictorSPX[] rightMotors = {
    new WPI_VictorSPX(Constants.rightMotorPorts[0]),
    new WPI_VictorSPX(Constants.rightMotorPorts[1])
  };

  private final DifferentialDrive driveTrain = new DifferentialDrive(leftMotors[0], rightMotors[0]);

  /**
   * this method is called when the DriveTrainSubsystem class is initialized.
  */
  public DriveSubsystem() {
    leftMotors[0].setNeutralMode(NeutralMode.Brake);
    leftMotors[1].setNeutralMode(NeutralMode.Brake);
    rightMotors[0].setNeutralMode(NeutralMode.Brake);
    rightMotors[1].setNeutralMode(NeutralMode.Brake);

    leftMotors[1].follow(leftMotors[0]);
    rightMotors[1].follow(rightMotors[0]);

    rightMotors[0].setInverted(true);
    rightMotors[1].setInverted(InvertType.FollowMaster);
  }

  /**
   * just call the arcadedrive method with a differential drive.
   */
  public void arcadeDrive(double speed, double rotation) {
    driveTrain.arcadeDrive(speed, rotation);
  }
}
