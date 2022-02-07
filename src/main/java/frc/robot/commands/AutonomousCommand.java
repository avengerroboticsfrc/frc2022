package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.io.IOException;
import java.nio.file.Path;

public class AutonomousCommand extends CommandBase {
  String trajectoryJson = "pathweaver/output/drive.wplib.json";
  Trajectory trajectory;

  /**
   * initializes the command.
   */
  public AutonomousCommand() {
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJson);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Cannot open trajectory: " + trajectoryJson, ex.getStackTrace());
    }
  }

  @Override
  public void execute() {

  }
}
