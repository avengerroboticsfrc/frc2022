package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Tester {
  final static TalonSRX motor = new TalonSRX(05);

  public static void runMotor() {

    motor.set(ControlMode.PercentOutput, 0.2);

  }
  public static void stopMotor() {
    motor.set(ControlMode.PercentOutput, 0.0);
  }

}
