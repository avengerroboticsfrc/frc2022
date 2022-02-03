package frc.robot.commands;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.file.Path;

//Builds path into the roboRIO

public class PathweaverLoader {
  String trajectoryJSON = "paths/drive.wplib.json";
  Trajectory trajectory = new Trajectory();

  @Override
  public void robotInit() {
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Cannot open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }
}
