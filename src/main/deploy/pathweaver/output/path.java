public class path {
    String trajectoryJSON = "paths/drive.wplib.json";
    Trajectory trajectory = new trajectory();

    @Override
    public void robotInit() {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath.resolve(trajectoryJSON);
            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
        }   catch (IOException ex) {
            DriverStation.reportError("Cannot open trajectory: " + trajectoryJSON, ex.getStackTrace());
        }
    }
}
