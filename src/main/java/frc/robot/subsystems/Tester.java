package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;


public class Tester {
  final static TalonSRX talon = new TalonSRX(05);

  public static void runMotor(){
  talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  talon.set(ControlMode.Position, 0);


  }

  public static void stopMotor() {
    talon.set(ControlMode.PercentOutput, 0.0);
  }

}
