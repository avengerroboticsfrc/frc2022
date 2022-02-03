package frc.robot.subsystems.drive;

import frc.robot.Constants;

public class MainDrive extends DriveTrain {
  /**
   * this method is called when the MainDrive class is initialized.
   * it calls the super class {@link DriveTrain} and instantiates it with
   * the 4 talons on the main robot.
   */
  public MainDrive() {
    super(Constants.MainRobot.leftDriveMotors.ports, Constants.MainRobot.rightDriveMotors.ports);
  }
}
