package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Gamepad {
  private final Joystick controller;

  public Gamepad(Joystick controller) {
    this.controller = controller;
  }

  public boolean getA() {
    return controller.getRawButton(1);
  }

  public double getLJoystickX() {
    return -1 * controller.getRawAxis(0);
  }

  public int getDPadAngle() {
    return controller.getPOV();
  }
}
