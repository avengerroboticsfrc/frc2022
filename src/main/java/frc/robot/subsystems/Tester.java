package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Tester {
  static final TalonSRX talon = new TalonSRX(05);

  public Tester() {
    talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    talon.setNeutralMode(NeutralMode.Brake);
    talon.setSelectedSensorPosition(0);
    talon.setSensorPhase(true);
  }
  

  public void runMotor() {
    talon.set(ControlMode.Position, 1000);
  }

  public void stopMotor() {
    talon.set(ControlMode.PercentOutput, 0.0);

  }
}
