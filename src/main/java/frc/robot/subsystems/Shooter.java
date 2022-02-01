package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;

public class Shooter {
  public final WPI_VictorSPX[] rightMotors = {
      new WPI_VictorSPX(Constants.shooterMotorPorts[0]),
      new WPI_VictorSPX(Constants.shooterMotorPorts[1])
  };
}
