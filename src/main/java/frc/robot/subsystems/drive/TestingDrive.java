package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;

public class TestingDrive extends DriveTrain {
  /**
   * this method is called when the TestingDrive class is initialized.
   * it calls the super class {@link DriveTrain} and instantiates it with
   * the 4 victors on the testing robot.
   */
  public TestingDrive() {
    super(
        new WPI_TalonFX[] {
            new WPI_TalonFX(Constants.testRobotLeftDriveMotorPorts[0]),
            new WPI_TalonFX(Constants.testRobotLeftDriveMotorPorts[1])
        },
        new WPI_TalonFX[] {
            new WPI_TalonFX(Constants.testRobotRightDriveMotorPorts[0]),
            new WPI_TalonFX(Constants.testRobotRightDriveMotorPorts[1])
        }
    );
  }
}
