package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;

public class DriverController {
    public final static Joystick controller = new Joystick(0);
    public final static double leftJoyX = controller.getRawAxis(1);
    public final static double leftJoyY = controller.getRawAxis(0);
    public final static boolean buttonA = controller.getRawButton(1);
    public final static boolean buttonB = controller.getRawButton(2);
    public final static boolean buttonX = controller.getRawButton(3);
    public final static boolean buttonY = controller.getRawButton(4);
    public final static boolean lBumper = controller.getRawButton(5);
    public final static boolean rBumper = controller.getRawButton(6);
    public final static boolean lMenu = controller.getRawButton(7);
    public final static boolean rMenu = controller.getRawButton(8);
    public final static boolean dPadUp = controller.getRawButton(7);
    public final static boolean dPadLeft = controller.getRawButton(7);
    public final static boolean dPadRight = controller.getRawButton(7);
    public final static boolean dPadDown = controller.getRawButton(7);
    public final static double lTrigger= controller.getRawAxis(1);
    public final static double rTrigger = controller.getRawAxis(7);

    //incorrect numbers
}
