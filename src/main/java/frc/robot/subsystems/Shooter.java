package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import frc.robot.HoodGains;
import frc.robot.TurretGains;
import frc.robot.subsystems.LimelightCamera;

//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {
  //Creates flywheel motors
  private final TalonFX flywheelMotor = new TalonFX(40);

  // Creates hood motor and sets PID values
  private final CANSparkMax hoodMotor = new CANSparkMax(50, MotorType.kBrushless);

  private SparkMaxPIDController hoodPidController;
  private RelativeEncoder hoodEncoder;

  // Creates turret motor and sets PID values
  public final WPI_TalonSRX turretMotor = new WPI_TalonSRX(49);
  public static final int kSlotIdx = 0;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
  public static boolean kSensorPhase = false;
  public static boolean kMotorInvert = false;
  public static final TurretGains kTurretGains = new TurretGains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
  public static final HoodGains kHoodGains = new HoodGains(0.15, 0, 1, 0, 0, 0, .3);

   

  private static final int TICKS_PER_ROTATION = 4096;

  // Shooter constructor
  public Shooter() {
    super();

    //Flywheel configuration
    flywheelMotor.configFactoryDefault();
    flywheelMotor.setNeutralMode(NeutralMode.Coast);
    flywheelMotor.configOpenloopRamp(1);
    
    //Hood configuration
    hoodMotor.restoreFactoryDefaults();
    hoodPidController = hoodMotor.getPIDController();
    hoodEncoder = hoodMotor.getEncoder();
    hoodPidController.setP(kHoodGains.kP);
    hoodPidController.setI(kHoodGains.kI);
    hoodPidController.setD(kHoodGains.kD);
    hoodPidController.setIZone(kHoodGains.kIz);
    hoodPidController.setFF(kHoodGains.kFF);
    hoodPidController.setOutputRange(kHoodGains.kMinOutput, kHoodGains.kMaxOutput);


    //Turret configuration
    turretMotor.configFactoryDefault();
    turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
    turretMotor.setSensorPhase(kSensorPhase);
    turretMotor.setInverted(kMotorInvert);
    /* Config the peak and nominal outputs, 12V means full */
    turretMotor.configNominalOutputForward(0, kTimeoutMs);
    turretMotor.configNominalOutputReverse(0, kTimeoutMs);
    turretMotor.configPeakOutputForward(1, kTimeoutMs);
    turretMotor.configPeakOutputReverse(-1, kTimeoutMs);
    /**
     * Config the allowable closed-loop error, Closed-Loop output will be
     * neutral within this range. See Table in Section 17.2.1 for native
     * units per rotation.
     */
    turretMotor.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);
    /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
    turretMotor.config_kF(kPIDLoopIdx, kTurretGains.kF, kTimeoutMs);
    turretMotor.config_kP(kPIDLoopIdx, kTurretGains.kP, kTimeoutMs);
    turretMotor.config_kI(kPIDLoopIdx, kTurretGains.kI, kTimeoutMs);
    turretMotor.config_kD(kPIDLoopIdx, kTurretGains.kD, kTimeoutMs);

    /**
     * Grab the 360 degree position of the MagEncoder's absolute
     * position, and intitally set the relative sensor to match.
     */
    int absolutePosition = turretMotor.getSensorCollection().getPulseWidthPosition();

    /* Mask out overflows, keep bottom 12 bits */
    absolutePosition &= 0xFFF;
    if (kSensorPhase) {
      absolutePosition *= -1;
    }
    if (kMotorInvert) {
      absolutePosition *= -1;
    }

    /* Set the quadrature (relative) sensor to match absolute */
    turretMotor.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

    turretMotor.configClearPositionOnLimitR(true, 50);
  }
 



  public void hoodTargeting() {
    // hoodMotor.set((LimelightCamera.getTargetXOffset())*.03);
    hoodPidController.setReference(4096, CANSparkMax.ControlType.kPosition);
  }




  public void flywheelTargeting() {
  }




  // Method to make flyWheelMotors Move
  public void flywheelPower(double speed) {
    flywheelMotor.set(ControlMode.Position, 1);
  }




  // Method to make hood move
  public void hoodPower(double speed) {
    hoodMotor.set(speed);
  }




  public void turn(double power) {
    turretMotor.set(power);
  }




  public void turnRotations(double ticks) {
    turretMotor.set(ControlMode.Position, ticks * TICKS_PER_ROTATION);
  }



  
  public double getTurnPosition() {
    return turretMotor.getSelectedSensorPosition(0);
  }
}
