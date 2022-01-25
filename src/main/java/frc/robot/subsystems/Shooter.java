package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
public class Shooter {
    public final WPI_VictorSPX[] rightMotors = {
        new WPI_VictorSPX(Constants.ShooterMotorPorts[0]),
        new WPI_VictorSPX(Constants.ShooterMotorPorts[1])
      };


}
