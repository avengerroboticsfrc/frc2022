package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final WPI_VictorSPX[] flyWheelMotors = {
      new WPI_VictorSPX(Constants.shooterMotorPorts[0]),
      new WPI_VictorSPX(Constants.shooterMotorPorts[1])
  };
  private final WPI_VictorSPX hoodMotor = new WPI_VictorSPX(Constants.hoodMotorPort);

  Shooter() {
    super();
  }
}
