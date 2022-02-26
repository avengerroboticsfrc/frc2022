package frc.robot.commands;

import frc.robot.subsystems.Lift;
import com.ctre.phoenix.motorcontrol.InvertType;
import frc.robot.subsystems.DriverController;

public class LiftControl {

    public void ManualLift(){
        Lift.verticalMotors[1].setInverted(InvertType.OpposeMaster);
        Lift.verticalMotors[1].follow(Lift.verticalMotors[0]);
        Lift.verticalMotors[0].set(DriverController.leftJoyY);
        Lift.verticalMotors[1].set(DriverController.leftJoyY);
    }
}
