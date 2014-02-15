package team1277.org.robot;

import com.sun.squawk.util.MathUtils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.Relay.Value;


public class ManualMethods {
	private final static int TANK_DRIVE_METHOD = 1;
	private final static int ARCADE_DRIVE_METHOD = 1;

	public final static int DRIVE_MODE_TANK = 1;
	public final static int DRIVE_MODE_ARCADE = 2;
	public static int driveMode = 2;

	public static void driveChain() {
		if (driveMode == DRIVE_MODE_TANK) {
			tankDrive();
		}
		else if (driveMode == DRIVE_MODE_ARCADE) {
			arcadeDrive();
		}
		else if (driveMode == 3) {
			relativeDrive();
		}
	}

	public static void tankDrive() {

		//Liams Tanks Drive
		if (TANK_DRIVE_METHOD == 1) {
			double speed = 1;
			double leftCoord = MainRobot.leftJoyStick.getY();
			double rightCoord = MainRobot.rightJoyStick.getY();

			MainRobot.setLeftMotors(leftCoord*speed);
			MainRobot.setRightMotors(rightCoord*speed);
		}
	}

	public static void arcadeDrive() {
		if (ARCADE_DRIVE_METHOD == 1) {
			adjustingArcadeDrive();
		}
		
		else if (ARCADE_DRIVE_METHOD == 2) {
			double y = MainRobot.rightJoyStick.getY();
			double x = MainRobot.rightJoyStick.getX();
			double dist = Math.sqrt(y*y+x*x);
			MainRobot.setLeftMotors((y-x)*dist);
			MainRobot.setRightMotors((y+x)*dist);
		}
		
	}

	public static void relativeDrive() {
		double y = MainRobot.rightJoyStick.getY();
		double x = MainRobot.rightJoyStick.getX();
		double speed = Math.sqrt(y*y+x*x);

		//speed *= .5;
		double ang = Math.toDegrees(MathUtils.atan2(y, x));
		if (ang<0)
			ang=360+ang;

		DriverStationLCD.getInstance().println(Line.kUser5, 1, String.valueOf(ang));
		//MainRobot.setLeftMotors(-speed/**change*/);
		//MainRobot.setRightMotors(speed/**change*/);
		if (Math.abs(Gyro.getAngle()-ang)>=5&&Math.abs(Gyro.getAngle()-ang)<=175) {
			if (speed<.13)
				return;
			double change = (Math.abs(Gyro.getAngle()-ang))/180d;
			double robAngle = Gyro.getAngle();
			if (robAngle>180) {
				robAngle-=180;
			}
			if (ang>=180) {
				//change = (Math.abs(Gyro.getAngle()-ang)-180d)/180d;
				ang-=180;
			}
			if (Math.abs(robAngle-ang)>=180) {
				change = (Math.abs(Gyro.getAngle()-ang)-180d)/180d;
			}
			DriverStationLCD.getInstance().println(Line.kUser6, 1, String.valueOf(change));
			if ((robAngle>ang+5&&Math.abs(robAngle-ang)<=180)||(robAngle<ang-5&&Math.abs(robAngle-ang)>180)) {

				MainRobot.setLeftMotors(speed*.5+change*speed);
				MainRobot.setRightMotors(-speed*.5+change*-speed);
				System.out.println("Right");
			}
			else if ((robAngle>ang+5&&Math.abs(robAngle-ang)>180)||(robAngle<ang-5&&Math.abs(robAngle-ang)<=180)) {
				MainRobot.setLeftMotors(-speed*.5+change*-speed);
				MainRobot.setRightMotors(speed*.5+change*speed);
				System.out.println("Left");
			}
			else
			{
				MainRobot.setLeftMotors(0);
				MainRobot.setRightMotors(0);
			}
		}
		else {
			if (Math.abs(Gyro.getAngle()-ang)>=5) {
				MainRobot.setLeftMotors(speed);
				MainRobot.setRightMotors(speed);
			}
			else {
				MainRobot.setLeftMotors(-speed);
				MainRobot.setRightMotors(-speed);
			}
		}
	}


	/**
	 * Start Liam's Arcade Drive
	 */

	private static void adjustingArcadeDrive() {
		ArcadeMode.arcadeDrive();
	}
	
	
	

	/**
	 * End Liam's Arcade Drive
	 */
	
	static boolean isGrabberOut = false;
	public static void pnumatics() {
		if (!isGrabberOut && MainRobot.leftJoyStick.getRawButton(3)) {
			isGrabberOut = true;
			//System.out.println("Grabber "+isGrabberOut);
		}
		else if (isGrabberOut && MainRobot.leftJoyStick.getRawButton(2)) {
			isGrabberOut = false;

			//System.out.println("Grabber "+isGrabberOut);
		}
		if (isGrabberOut) {
			MainRobot.releaseSol.set(true);
			MainRobot.retractSol.set(false);
		} else {
			MainRobot.releaseSol.set(false);
			MainRobot.retractSol.set(true);
		}
		//MainRobot.compressor.set(Value.kForward);
		
		if (MainRobot.pressureSwitch.get()) {
			MainRobot.compressor.set(Value.kOff);
		}
		else
		{
			MainRobot.compressor.set(Value.kForward);
		}
		//DriverStationLCD.getInstance().println(Line.kUser3, 1, String.valueOf(MainRobot.pressureSwitch.get()));
		//System.out.println("ON?");
	}
	
	public static boolean ballGrabberOn = false;
	public static boolean ballGrabButton = false;
	
	public static void ballGrabber() {
		if (ballGrabberOn&&!ballGrabButton&&MainRobot.leftJoyStick.getRawButton(1)) {
			ballGrabberOn = false;
			ballGrabButton = true;
		}
		else if (!ballGrabberOn&&!ballGrabButton&&MainRobot.leftJoyStick.getRawButton(1)) {
			ballGrabberOn = true;
			ballGrabButton = true;
		}
		else if (!MainRobot.leftJoyStick.getRawButton(1)) {
			ballGrabButton = false;
		}
		if (ballGrabberOn) {
			MainRobot.ballGrabber.set(Value.kOn);
			MainRobot.ballGrabber.set(Value.kForward);

			System.out.println("Test Motor");
		}
		else  {
			MainRobot.ballGrabber.set(Value.kOff);
		}
		DriverStationLCD.getInstance().println(Line.kUser3, 1, String.valueOf(ballGrabberOn));
		
	}
	
	public static void initPnumatics() {
		isGrabberOut = false;
		ballGrabberOn = false;
	}
}
