package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Tester {
  static final TalonSRX talon = new TalonSRX(05);

  public static void runMotor() {
    talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    talon.set(ControlMode.Position, 0);
  }

  public static void stopMotor() {
    talon.set(ControlMode.PercentOutput, 0.0);
  }
}
