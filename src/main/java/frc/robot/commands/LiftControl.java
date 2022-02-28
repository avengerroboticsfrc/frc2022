package frc.robot.commands;

import frc.robot.subsystems.Lift;
import com.ctre.phoenix.motorcontrol.InvertType;
import frc.robot.subsystems.Gamepad;

public class LiftControl {
    Gamepad controller;

    public void ManualLift(){
        Lift.verticalMotors[1].setInverted(InvertType.OpposeMaster);
        Lift.verticalMotors[1].follow(Lift.verticalMotors[0]);
        Lift.verticalMotors[0].set(controller.getLJoystickY());
        Lift.verticalMotors[1].set(controller.getLJoystickY());
    }
}
