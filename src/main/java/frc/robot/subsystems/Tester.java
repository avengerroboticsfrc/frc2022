package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.*;



public class Tester {
  static final TalonSRX talon = new TalonSRX(05);
  static final int kSensorUnitsPerRotation = 4096;
  static final int kMaxRPM = 14000;
  static final int kGearRatio = 15;
  Faults _faults = new Faults();
  Joystick joystick = new Joystick(0);
  double xSpeed;
  public Tester() {
    talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    talon.setNeutralMode(NeutralMode.Brake);
    talon.setSelectedSensorPosition(0);
    talon.setSensorPhase(true);
    talon.configClosedloopRamp(1);
    talon.configOpenloopRamp(5);
  }
  
  public void runMotor()  {

  }

  public void testMotor() {
    xSpeed = joystick.getRawAxis(1) * -1; // make forward stick positive

    /* update motor controller */
    talon.set(ControlMode.PercentOutput, xSpeed);
    /* check our live faults */
    talon.getFaults(_faults);
    /* hold down btn1 to print stick values */
    if (joystick.getRawButton(1)) {
      System.out.println("Sensor Vel:" + talon.getSelectedSensorVelocity());
      System.out.println("Sensor Pos:" + talon.getSelectedSensorPosition());
      System.out.println("Out %" + talon.getMotorOutputPercent());
      System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);
    }
  }

  public void stopMotor() {
    talon.set(ControlMode.PercentOutput, 0.0);

  }
}
