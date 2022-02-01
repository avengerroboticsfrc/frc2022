package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class MainDrive extends DriveTrain {
  /**
   * this method is called when the FinalDrive class is initialized.
   */
  public MainDrive() {
    super(
        new WPI_TalonFX[] {
            new WPI_TalonFX(Constants.leftDriveMotorPorts[0]),
            new WPI_TalonFX(Constants.leftDriveMotorPorts[1])
        },
        new WPI_TalonFX[] {
            new WPI_TalonFX(Constants.rightDriveMotorPorts[0]),
            new WPI_TalonFX(Constants.rightDriveMotorPorts[1])
        }
    );
  }
}
