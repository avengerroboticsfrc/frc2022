package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.DriverController;

public class ShooterControl {
    public void manualFlywheel(){

    }
    
    public void manualHood(){
    if(DriverController.dPadUp){
        Shooter.hoodMotor.set(.2);
    }

    }

    public void manualTurret(){

    }
}
