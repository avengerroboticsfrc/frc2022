package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Tester {
  final static Spark motor = new Spark(5);

  public static void runMotor() {

    motor.set(.2);

  }
  public static void stopMotor() {
    motor.set(0);
  }

}
