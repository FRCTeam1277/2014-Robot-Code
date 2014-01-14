package team1277.org.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

public class MainRobot extends IterativeRobot {
	//Declaring static components
	public static Jaguar rightMotors;
	public static Jaguar leftMotors;
	
	public static AnalogChannel leftRangeFinder;
	public static AnalogChannel rightRangeFinder;
	
	public static Joystick rightJoyStick;
	public static Joystick leftJoyStick;
	
	
	/**
	 * The current state of the robot
	 */
	public static int state;
	
	
	public MainRobot() {
		super();
	}
	
	public void robotInit() {
		System.out.println("Robot Started, Hello Dave.");
		leftMotors = new Jaguar(Ports.LEFT_DRIVE_PORT);
		rightMotors = new Jaguar(Ports.RIGHT_DRIVE_PORT);
		
		leftJoyStick = new Joystick(Ports.LEFT_JOYSTICK);
		rightJoyStick = new Joystick(Ports.RIGHT_JOYSTICK);
		
		leftRangeFinder = new AnalogChannel(Ports.LEFT_RANGE_FINDER);
		rightRangeFinder = new AnalogChannel(Ports.RIGHT_RANGE_FINDER);
	}
	
	public void disabledInit() {
		leftMotors.set(0);
		rightMotors.set(0);
	}
	
	public void disabledPeriodic() {
		
	}
	
	public void teleopInit() {
		state = States.TELEOP_MANUAL_DRIVE;
		ManualMethods.driveMode = ManualMethods.DRIVE_MODE_TANK;
	}
	
	public void teleopPeriodic() {
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
		else {
			DriverStationLCD.getInstance().println(Line.kUser2, 1, " ");
		}
		DriverStationLCD.getInstance().println(Line.kUser1, 1, "Mode "+state);
		DriverStationLCD.getInstance().updateLCD();
	}
	
	public void autonomousInit() {
		
	}
	
	public void autonomousPeriodic() {
		
	}
	
}
