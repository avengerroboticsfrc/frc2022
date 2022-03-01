package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
<<<<<<< HEAD
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
=======
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
>>>>>>> b6b58bda299b1f41eed257b99a4631da540a9cf9
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TurretGains;
import frc.robot.subsystems.LimelightCamera;

//Creates Shooter Class with its parent class as SubsystemBase
public class Shooter extends SubsystemBase {
<<<<<<< HEAD

  public XboxController controller = new XboxController(0);
  
  //Creates flywheel motors
=======
  // Creates flywheel motors
>>>>>>> b6b58bda299b1f41eed257b99a4631da540a9cf9
  private final CANSparkMax flywheelMotor1 = new CANSparkMax(40, MotorType.kBrushless);
  private final CANSparkMax flywheelMotor2 = new CANSparkMax(41, MotorType.kBrushless);

  // Creates hood motor and sets PID values
  private final CANSparkMax hoodMotor = new CANSparkMax(50, MotorType.kBrushless);
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;

  // Creates turret motor and sets PID values
  public final WPI_TalonSRX turretMotor = new WPI_TalonSRX(49);
  public static final int kSlotIdx = 0;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
  public static boolean kSensorPhase = false;
  public static boolean kMotorInvert = false;
  public static final TurretGains kTurretGains = new TurretGains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
  public static final int kTimeoutms = 100;

  private final WPI_TalonSRX turretTurner;

  private static final int TICKS_PER_ROTATION = 4096;

  // Shooter constructor
  public Shooter() {
    super();

    flywheelMotor1.restoreFactoryDefaults();
    flywheelMotor2.restoreFactoryDefaults();
    flywheelMotor1.setIdleMode(IdleMode.kCoast);
    flywheelMotor2.setIdleMode(IdleMode.kCoast);
    flywheelMotor2.follow(flywheelMotor1, true);
    flywheelMotor1.setInverted(true);
<<<<<<< HEAD
    flywheelMotor1.setOpenLoopRampRate(1);
    
=======
    flywheelMotor1.setOpenLoopRampRate(3);
>>>>>>> b6b58bda299b1f41eed257b99a4631da540a9cf9

    hoodMotor.restoreFactoryDefaults();
    m_pidController = hoodMotor.getPIDController();
    m_encoder = hoodMotor.getEncoder();
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

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


    turretTurner = new WPI_TalonSRX(Constants.MainRobot.turretTurnMotor.ports[0]);
    turretTurner.configClearPositionOnLimitR(true, 50);
  }

  public void hoodTargeting() {
    // hoodMotor.set((LimelightCamera.getTargetXOffset())*.03);
    m_pidController.setReference(4096, CANSparkMax.ControlType.kPosition);
  }

  public void flywheelTargeting() {
  }

<<<<<<< HEAD

  public void flywheelTest(){
    flywheelMotor1.set(controller.getRawAxis(0)*-1);
  }

public void turn(double d) {
}

public int getTurnPosition() {
	return 0;
}

public void turnRotations(int i) {
}

}
=======
  public void flywheelTest() {
    flywheelMotor1.set(1);
  }

  // Method to make flyWheelMotors Move
  public void flywheelPower(double speed) {
    flywheelMotor1.set(speed);
  }

  // Method to make hood move
  public void hoodPower(double speed) {
    hoodMotor.set(speed);
  }

  public void turn(double power) {
    turretTurner.set(power);
  }
>>>>>>> b6b58bda299b1f41eed257b99a4631da540a9cf9

  public void turnRotations(double ticks) {
    turretTurner.set(ControlMode.Position, ticks * TICKS_PER_ROTATION);
  }

  public double getTurnPosition() {
    return turretTurner.getSelectedSensorPosition(0);
  }
}
