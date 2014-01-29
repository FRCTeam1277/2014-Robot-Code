package team1277.org.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

public class MainRobot extends IterativeRobot {
	//Declaring static components
	public static Jaguar rightMotor1;
	public static Jaguar rightMotor2;
	public static Jaguar leftMotor1;
	public static Jaguar leftMotor2;

	public static AnalogChannel leftRangeFinder;
	public static AnalogChannel rightRangeFinder;

	public static boolean useXBox = false;

	public static RobotJoystick rightJoyStick;
	public static RobotJoystick leftJoyStick;
	
	public static Relay frontLight;
	public static Relay backLight;

	private Joystick xbox;

	public static AnalogChannel gyro;



	/**
	 * The current state of the robot
	 */
	public static int state;

	//Hello World

	public MainRobot() {
		super();
	}


	public void robotInit() {
		System.out.println("Robot Started, Hello Dave.");
		xbox = new Joystick(1);
		useXBox = DriverStation.getInstance().getDigitalIn(1);
		if (useXBox) {
			leftJoyStick = new RobotJoystick(xbox, Hand.kRight,true);
			rightJoyStick = new RobotJoystick(xbox, Hand.kLeft,true);
		}
		else {
			leftJoyStick = new RobotJoystick(Ports.LEFT_JOYSTICK);
			rightJoyStick = new RobotJoystick(Ports.RIGHT_JOYSTICK);
		}
		System.out.println("Hello World");
		leftMotor1 = new Jaguar(Ports.LEFT_DRIVE_PORT_1);
		rightMotor1 = new Jaguar(Ports.RIGHT_DRIVE_PORT_1);

		leftMotor2 = new Jaguar(Ports.LEFT_DRIVE_PORT_2);
		rightMotor2 = new Jaguar(Ports.RIGHT_DRIVE_PORT_2);

		leftJoyStick = new RobotJoystick(Ports.LEFT_JOYSTICK);
		rightJoyStick = new RobotJoystick(Ports.RIGHT_JOYSTICK);

		leftRangeFinder = new AnalogChannel(Ports.LEFT_RANGE_FINDER);
		rightRangeFinder = new AnalogChannel(Ports.RIGHT_RANGE_FINDER);
		gyro = new AnalogChannel(Ports.GYRO);
		
		frontLight = new Relay(Ports.FRONT_LIGHT);
		backLight = new Relay(Ports.BACK_LIGHT);



	}


	public void disabledInit() {
		setLeftMotors(0);
		setRightMotors(0);
	}


	public void disabledPeriodic() {

	}
	long lastTime = 0;

	public void teleopInit() {
		useXBox = DriverStation.getInstance().getDigitalIn(1);
		if (useXBox) {
			leftJoyStick = new RobotJoystick(xbox, Hand.kRight,true);
			rightJoyStick = new RobotJoystick(xbox, Hand.kLeft,true);
		}
		else {
			leftJoyStick = new RobotJoystick(Ports.LEFT_JOYSTICK);
			rightJoyStick = new RobotJoystick(Ports.RIGHT_JOYSTICK);
		}
		state = States.TELEOP_MANUAL_DRIVE;
		ManualMethods.driveMode = ManualMethods.DRIVE_MODE_TANK;
		Gyro.init();
		lastTime = System.currentTimeMillis();
	}


	public void teleopPeriodic() {

		int delta = (int)(System.currentTimeMillis()-lastTime);
		//		DriverStationLCD.getInstance().println(Line.kUser3, 1, String.valueOf(gyro.getAverageVoltage()));
		//		DriverStationLCD.getInstance().println(Line.kUser4, 1, String.valueOf(Gyro.getAngle()));
		Gyro.updateAngle(((double)delta)/1000d);
		lastTime = System.currentTimeMillis();
		if (state == States.TELEOP_MANUAL_DRIVE) {
			ManualMethods.driveChain();
		}
		else if (state == States.TELEOP_AUTOMATIC_LINEUP) {
			AutonomousMethods.lineUp();
		}

		if (rightJoyStick.getRawButton(1)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "1");

			state = States.TELEOP_AUTOMATIC_LINEUP;
		}
		//System.out.println("t");

		else if (rightJoyStick.getRawButton(2)) {
			ManualMethods.driveMode = ManualMethods.DRIVE_MODE_TANK;
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "2");
			state = States.TELEOP_MANUAL_DRIVE;
		}

		else if (rightJoyStick.getRawButton(3)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "3");
			ManualMethods.driveMode = ManualMethods.DRIVE_MODE_ARCADE;
			state = States.TELEOP_MANUAL_DRIVE;
		}
		else if (rightJoyStick.getRawButton(4)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "4");
			ManualMethods.driveMode = 3;
			state = States.TELEOP_MANUAL_DRIVE;
		}
		else {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, " ");
		}
		DriverStationLCD.getInstance().println(Line.kUser1, 1, "kMode "+state);
		DriverStationLCD.getInstance().updateLCD();
		
		LightPatterns.update(delta);
	}


	public void autonomousInit() {

	}


	public void autonomousPeriodic() {

	}

	public static void setRightMotors(double val) {
		rightMotor1.set(val);
		rightMotor2.set(val);
	}

	public static void setLeftMotors(double val) {
		leftMotor1.set(val);
		leftMotor2.set(val);
	}

}
