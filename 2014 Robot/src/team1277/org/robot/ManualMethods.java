package team1277.org.robot;

import com.sun.squawk.util.MathUtils;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;


public class ManualMethods {
	private final static int TANK_DRIVE_METHOD = 1;
	private final static int ARCADE_DRIVE_METHOD = 2;

	public final static int DRIVE_MODE_TANK = 1;
	public final static int DRIVE_MODE_ARCADE = 2;
	
	public final static int KICK_MODE_LOAD = 1;
	public final static int KICK_MODE_FIRE = 2;
	
	public static int driveMode = 1;
	public static int kickMode = 1;
	//This is: WHICH DRIVE DO WE DO?  Based on value set in MainRobot.
	public static void driveChain() {
		if (driveMode == DRIVE_MODE_TANK) {
			tankDrive();
		}
		else if (driveMode == DRIVE_MODE_ARCADE) {
			arcadeDrive();
		}
		else if (driveMode == 3) {
			//relitiveDrive();
		}
	}
	public static void useKicker(){
		//LOAD
		if(kickMode == 1){
			loadKicker();
		}
		
		//FIRE
		if(kickMode == 2){
			releaseKicker();
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
		//Liams: BETTER
		if (ARCADE_DRIVE_METHOD == 1) {
			adjustingArcadeDrive();
		}
		//Nicks: WORKS
		else if (ARCADE_DRIVE_METHOD == 2) {
			double y = MainRobot.rightJoyStick.getY();
			double x = MainRobot.rightJoyStick.getX();
			double dist = Math.sqrt(y*y+x*x);
			MainRobot.setLeftMotors((y-x)*dist);
			MainRobot.setRightMotors((y+x)*dist);
		}
	}
	
	//GYRO STUFF
	/*
	public static void relitiveDrive() {
		double y = MainRobot.rightJoyStick.getY();
		double x = MainRobot.rightJoyStick.getX();
		double speed = Math.sqrt(y*y+x*x);

		//speed *= .5;
		double ang = Math.toDegrees(MathUtils.atan2(y, x));
		if (ang<0)
			ang=360+ang;

		DriverStationLCD.getInstance().println(Line.kUser5, 1, String.valueOf(ang));
		//MainRobot.setLeftMotors(-speed/**change*//*);ADDED THE LAST COMMENT SYMBOL
		//MainRobot.setRightMotors(speed/**change*//*);
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
	 * Start Liam's Arcade Drive(This is HUGE)
	 */

	private static void adjustingArcadeDrive() {
		double leftX = MainRobot.leftJoyStick.getX();
		double leftY = MainRobot.leftJoyStick.getY();

		double slope = 0.0;

		try{
			if(leftX>0){
				slope = ((leftY - 0)/(leftX - 0));
			}
			if(leftX<0){
				slope = ((leftY - 0)/(leftX - 0)) * -1;

			}



		}
		catch(Exception e){

			return;


		}
		double movedSlope = slope * 100;

		int intSlope = (int) movedSlope;




		if(intSlope == 0){
			if(leftY>0){
				//System.out.println("FOREWARD----------------------------");
				MainRobot.setLeftMotors(leftX + leftY);
				MainRobot.setRightMotors(leftX + leftY);
				return;
			}
			if(leftY<0){
				//System.out.println("BACKWARD----------------------------");
				MainRobot.setLeftMotors(leftX + leftY);
				MainRobot.setRightMotors(leftX + leftY);
				return;
			}
			if(leftX>0){
				//System.out.println("RIGHT----------------------------");
				MainRobot.setLeftMotors(leftX);
				MainRobot.setRightMotors(leftX * -1);
				return;
			}
			if(leftX<0){
				//System.out.println("LEFT----------------------------");
				MainRobot.setLeftMotors(leftX* -1);
				MainRobot.setRightMotors(leftX);
				return;
			}

			if(leftY == 0){
				MainRobot.setLeftMotors(0);
				MainRobot.setRightMotors(0);
				return;
			}
		}

		//RIGHT
		if(leftX>0){
			if(intSlope>150){
				//System.out.println("Up Up Right");
				MainRobot.setLeftMotors((leftX + leftY)/2);
				MainRobot.setRightMotors((leftX + leftY)/2);
				return;
			}
			if(intSlope>=50 && intSlope <= 150){
				//System.out.println("Up Right");
				MainRobot.setLeftMotors((leftX + leftY)/2);
				MainRobot.setRightMotors((leftX + leftY)/4);
				return;
			}
			if(intSlope>=-50 && intSlope < 50){

				//System.out.println("Straight Right");
				MainRobot.setLeftMotors((leftX + leftY));
				MainRobot.setRightMotors(((leftX + leftY))*-1);
				return;
			}

			if(intSlope>=-150 && intSlope < -50){
				//System.out.println("Bottom Right:::LEFT X: " + leftX + " LEFT Y " + leftY);

				MainRobot.setLeftMotors(((Math.abs(leftX) + Math.abs(leftY))/2) * -1);

				MainRobot.setRightMotors(((Math.abs(leftX) + Math.abs(leftY))/4)* -1);

				return;
			}

			if(intSlope<-150){
				//System.out.println("Bottom Bottom Right");
				MainRobot.setLeftMotors(leftY);
				MainRobot.setRightMotors(leftY);
				return;
			}
		}
		//LEFT
		if(leftX<0){
			if(intSlope>150){
				//System.out.println("Up Up Left");
				MainRobot.setLeftMotors((leftX + leftY)/2);
				MainRobot.setRightMotors((leftX + leftY)/2);
				return;
			}
			if(intSlope>=50 && intSlope <= 150){
				//System.out.println("Up Left");
				MainRobot.setLeftMotors((Math.abs(leftX) + Math.abs(leftY))/4);
				MainRobot.setRightMotors((Math.abs(leftX) + Math.abs(leftY))/2);
				return;

			}
			if(intSlope>=-50 && intSlope < 50){
				//System.out.println("Straight Left");
				MainRobot.setLeftMotors((leftX + leftY)*-1);
				MainRobot.setRightMotors((leftX + leftY));
				return;
			}

			if(intSlope>=-150 && intSlope < -50){
				//System.out.println("Bottom Left");
				MainRobot.setLeftMotors(((leftX + leftY)/4));
				MainRobot.setRightMotors(((leftX + leftY)/2));
				return;
			}

			if(intSlope<-150){
				//System.out.println("Bottom Bottom Left");
				MainRobot.setLeftMotors(leftY);
				MainRobot.setRightMotors(leftY);
				return;
			}
		}
		}

	

	/**
	 * End Liam's Arcade Drive
	 */
	private static void loadKicker(){
		
	}
	private static void releaseKicker(){
		
	}
	
}