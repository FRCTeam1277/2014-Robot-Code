package team1277.org.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class MainRobot extends IterativeRobot {
	//Declaring static components
	public static Jaguar rightMotor1;
	//public static Jaguar rightMotor2;
	public static Jaguar leftMotor1;
	//public static Jaguar leftMotor2;
	
	public static Jaguar winchRelease;
	
	public static AnalogChannel leftRangeFinder;
	public static AnalogChannel rightRangeFinder;
	
	
	public static RobotJoystick rightJoyStick;
	public static RobotJoystick leftJoyStick;
	
	public static boolean useXBox = false;
	
	private Joystick xbox;
	
	public static Jaguar winch;
	
	public static DigitalInput armLimit;
	public static DigitalInput clutchOpenLimit;
	public static DigitalInput clutchCloseLimit;
	
	public static AnalogChannel gyro;
	
	public static AnalogChannel winchPot;
	//public static DigitalInput winc
	
	public static Relay arms;
	
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
		leftMotor1 = new Jaguar(Ports.LEFT_DRIVE_PORT_1);
		rightMotor1 = new Jaguar(Ports.RIGHT_DRIVE_PORT_1);

		//leftMotor2 = new Jaguar(Ports.LEFT_DRIVE_PORT_2);
		//rightMotor2 = new Jaguar(Ports.RIGHT_DRIVE_PORT_2);

		useXBox = !DriverStation.getInstance().getDigitalIn(1);
		if (useXBox) {
			leftJoyStick = new RobotJoystick(xbox, Hand.kRight,true);
			rightJoyStick = new RobotJoystick(xbox, Hand.kLeft,true);
		}
		else {
			leftJoyStick = new RobotJoystick(Ports.LEFT_JOYSTICK);
			rightJoyStick = new RobotJoystick(Ports.RIGHT_JOYSTICK);
		}
		//leftRangeFinder = new AnalogChannel(Ports.LEFT_RANGE_FINDER);
		//rightRangeFinder = new AnalogChannel(Ports.RIGHT_RANGE_FINDER);
		//gyro = new AnalogChannel(Ports.GYRO);
		
		winch = new Jaguar(Ports.WINCH_RETRACT_PORT);
		winchRelease = new Jaguar(Ports.WINCH_RELEASE_PORT);
		
		//leftMotor2 = new Jaguar(Ports.LEFT_DRIVE_PORT_2);
		//rightMotor2 = new Jaguar(Ports.RIGHT_DRIVE_PORT_2);

		useXBox = !DriverStation.getInstance().getDigitalIn(1);
		if (useXBox) {
			leftJoyStick = new RobotJoystick(xbox, Hand.kRight,true);
			rightJoyStick = new RobotJoystick(xbox, Hand.kLeft,true);
		}
		else {
			leftJoyStick = new RobotJoystick(Ports.LEFT_JOYSTICK);
			rightJoyStick = new RobotJoystick(Ports.RIGHT_JOYSTICK);
		}
		//leftRangeFinder = new AnalogChannel(Ports.LEFT_RANGE_FINDER);
		//rightRangeFinder = new AnalogChannel(Ports.RIGHT_RANGE_FINDER);
		//gyro = new AnalogChannel(Ports.GYRO);
		armLimit = new DigitalInput(Ports.ARM_RETRACTED_LIMIT_PORT);
		clutchOpenLimit = new DigitalInput(Ports.WINCH_HOLD_OPEN_PORT);
		clutchCloseLimit = new DigitalInput(Ports.WINCH_HOLD_CLOSED_PORT);
		
		winchPot = new AnalogChannel(Ports.WINCH_POT_PORT);
		
		arms = new Relay(Ports.ARMS_RELAY_PORT);
	}
	
	
	public void disabledInit() {
		setLeftMotors(0);
		setRightMotors(0);
		winch.set(0);
		winchRelease.set(0);
		arms.set(Value.kOff);
	}
	
	
	public void disabledPeriodic() {
		
	}
	
	
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
		
		KickerWinch.init(KickerWinch.INIT_STATE_RELAXED);
                //Gyro.init();
                //Gyro.init();
		//INFO-INFO-INFO
		//BUTTON MAP
		//1 = Automatic Lineup Mode//
		//2 = Tank Drive
		//3 = Arcade Drive
		//4 = Load Kicker
		//5 = Fire Kicker
	}
	
	static long time = 0;
	static long lastFrame = -1;
	
	static boolean r3Down = false;
	static long lastR3 = -1;
	static boolean armsInUse = false;
	static boolean armsGoingUp = false;
	
	public void teleopPeriodic() {
		DriverStationLCD.getInstance().clear();
		//COMMENTED ALL GYROSTUFF
		//DriverStationLCD.getInstance().println(Line.kUser3, 1, String.valueOf(gyro.getAverageVoltage()));
		//DriverStationLCD.getInstance().println(Line.kUser4, 1, String.valueOf(Gyro.getAngle()));
		//Gyro.updateAngle(20d/1000d);
		ManualMethods.driveChain();
		/*if (state == States.TELEOP_MANUAL_DRIVE) {
			ManualMethods.driveChain();
		}
		else if (state == States.TELEOP_AUTOMATIC_LINEUP) {
			AutonomousMethods.lineUp();
		}
		//TANK DRIVE

		else if (state == States.TELEOP_AUTOMATIC_LOAD) {
			ManualMethods.useKicker();
		}
		else if (state == States.TELEOP_AUTOMATIC_RELEASE) {
			ManualMethods.useKicker();
		}
		//AUTO LINEUP
		if (rightJoyStick.getRawButton(1)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "1");
			
			state = States.TELEOP_AUTOMATIC_LINEUP;
		}
		
		else if (rightJoyStick.getRawButton(2)) {
			ManualMethods.driveMode = ManualMethods.DRIVE_MODE_TANK;
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "2");
			state = States.TELEOP_MANUAL_DRIVE;
		}
		//ARCADE DRIVE
		else if (rightJoyStick.getRawButton(3)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "3");
			ManualMethods.driveMode = ManualMethods.DRIVE_MODE_ARCADE;
			state = States.TELEOP_MANUAL_DRIVE;
		}
		//LOAD
		else if (rightJoyStick.getRawButton(4)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "4");
			ManualMethods.kickMode = ManualMethods.KICK_MODE_LOAD;
			state = States.TELEOP_AUTOMATIC_LOAD;
		}
		//FIRE
		else if (rightJoyStick.getRawButton(5)) {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, "5");
			ManualMethods.kickMode = ManualMethods.KICK_MODE_FIRE;
		}
		else {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, " ");
		}*/
		
		if (rightJoyStick.getRawButton(4)) {
			arms.set(Value.kReverse);
		}
		else if (rightJoyStick.getRawButton(5)) {
			arms.set(Value.kForward);
		}
		else {
				
		
			
			boolean flag = true;
			if (rightJoyStick.getRawButton(3) && !armsInUse) {
				if (!r3Down) {
					lastR3 = System.currentTimeMillis();
					r3Down = true;
					armsGoingUp = true;
					armsInUse = true;
				}
				
			} else {
				if (r3Down) {
					lastR3 = System.currentTimeMillis();
					r3Down = false;
					armsGoingUp = false;
					armsInUse = true;
				}
				
			}
			if (armsInUse && armsGoingUp) {
				if (System.currentTimeMillis()-lastR3<500) {
					arms.set(Value.kForward);
					flag = false;
				}
				else {
					armsInUse = false;
				}
			}
			else if (armsInUse && !armsGoingUp){
				if (System.currentTimeMillis()-lastR3<500) {
					arms.set(Value.kReverse);
					flag = false;
				}
				else {
					armsInUse = false;
				}
			}
			if (flag) {
				arms.set(Value.kOff);
			}
		}
		
		if (leftJoyStick.getRawButton(4)) {
			if (leftJoyStick.getRawButton(1)) {
				winchRelease.set(.5);
			}
			else {
				winchRelease.set(1);
			}
		}
		else if (leftJoyStick.getRawButton(5)) {
			if (leftJoyStick.getRawButton(1)) {
				winchRelease.set(-.5);
			}
			else {
				winchRelease.set(-1);
			}
		}
		else {
			winchRelease.set(0);
		}
		
		if (leftJoyStick.getRawButton(3)) {
			if (lastFrame != -1) {
				time += System.currentTimeMillis()-lastFrame;
			}
			lastFrame = System.currentTimeMillis();
			winch.set(1);
		}
		else if(leftJoyStick.getRawButton(2)) {
			if (lastFrame != -1) {
				time += System.currentTimeMillis()-lastFrame;
			}
			lastFrame = System.currentTimeMillis();
			winch.set(-1);
		}
		else {
			lastFrame = -1;
			winch.set(0);
		}
		
		/*if (leftJoyStick.getRawButton(1)) {
			time = 0;
		}*/
		
		
		if (leftJoyStick.getRawButton(1)) {
			KickerWinch.reload();
		}
		
		if (rightJoyStick.getRawButton(1)) {
			KickerWinch.launch();
		}
		
		if (!rightJoyStick.getRawButton(2)) {
			KickerWinch.onLoop();
		}
		
		
		DriverStationLCD.getInstance().println(Line.kUser1, 1, "Test Mode "+state);
		DriverStationLCD.getInstance().println(Line.kUser2, 1, String.valueOf(clutchOpenLimit.get()));
		DriverStationLCD.getInstance().println(Line.kUser3, 1, String.valueOf(clutchCloseLimit.get()));
		DriverStationLCD.getInstance().println(Line.kUser4, 1, String.valueOf(winchPot.getVoltage()));
		//DriverStationLCD.getInstance().println(Line.kUser5, 1, String.valueOf(time));
		DriverStationLCD.getInstance().updateLCD();
	}
	
	
	public void autonomousInit() {
		
	}
	
	
	public void autonomousPeriodic() {
		
	}
	
	public static void setRightMotors(double val) {
		rightMotor1.set(val);
		//rightMotor2.set(val);
	}
	
	public static void setLeftMotors(double val) {
		leftMotor1.set(val);
		//leftMotor2.set(val);
	}
	
}