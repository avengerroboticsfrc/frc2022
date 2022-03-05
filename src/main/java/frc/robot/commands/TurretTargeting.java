package frc.robot.commands;

import frc.robot.subsystems.LimelightCamera;
import frc.robot.subsystems.Shooter;

public class TurretTargeting {
    private LimelightCamera limelight = new LimelightCamera();
    private Shooter turret = new Shooter();

    public TurretTargeting() {
    }

    public void AimTurret() {
        double Kp = -0.1f;
        double min_command = 0.05f;
        double tx = limelight.getTargetXOffset();
        double heading_error = tx *-1;
        double steering_adjust = 0.0f;
        
        if (tx > 1.0)
        {
                steering_adjust = Kp*heading_error - min_command;
        }
        else if (tx < 1.0)
        {
                steering_adjust = Kp*heading_error + min_command;
        }
        turret.turnRotations(steering_adjust);
    
    }
}
