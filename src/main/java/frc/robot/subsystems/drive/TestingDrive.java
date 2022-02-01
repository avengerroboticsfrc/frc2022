package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;

public class TestingDrive extends DriveTrain {
  /**
   * this method is called when the TestingDrive class is initialized.
   */
  public TestingDrive() {
    super(
        new WPI_VictorSPX[] {
            new WPI_VictorSPX(Constants.leftDriveMotorPorts[0]),
            new WPI_VictorSPX(Constants.leftDriveMotorPorts[1])
        },
        new WPI_VictorSPX[] {
            new WPI_VictorSPX(Constants.rightDriveMotorPorts[0]),
            new WPI_VictorSPX(Constants.rightDriveMotorPorts[1])
        }
    );
  }
}
