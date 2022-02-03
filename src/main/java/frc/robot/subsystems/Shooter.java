package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private final WPI_VictorSPX[] flyWheelMotors = {
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[0]),
      new WPI_VictorSPX(Constants.MainRobot.shooterMotor.ports[1])
  };
  private final WPI_VictorSPX hoodMotor = new WPI_VictorSPX(Constants.MainRobot.hoodMotor.ports[0]);

  Shooter() {
    super();
  }
}
