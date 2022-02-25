package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;

public class DriverController {
    public static final Joystick controller = new Joystick(0);
    public final double leftJoystickX = controller.getRawAxis(1);
    public final double leftJoystickY = controller.getRawAxis(0);
    public int bruh;
    //incorrect numbers
}
