package frc.robot.subsystems.drive;

import frc.robot.Constants;

public class TestingDrive extends DriveTrain {
  /**
   * this method is called when the TestingDrive class is initialized.
   * it calls the super class {@link DriveTrain} and instantiates it with
   * the 4 victors on the testing robot.
   */
  public TestingDrive() {
    super(
        Constants.TestingRobot.leftDriveMotors.ports,
        Constants.TestingRobot.rightDriveMotors.ports
    );
  }
}
