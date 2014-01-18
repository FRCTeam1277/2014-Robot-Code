//TEST
package team1277.org.robot;

import edu.wpi.first.wpilibj.AnalogChannel;

public class AutonomousMethods {
	public static final int LINE_UP_METHOD = 1;


	//Hello World
	//Hello Again
	


	public static void lineUp() {
		if (LINE_UP_METHOD == 1) {
			lineUpWithAdjust();
		}
		else if (LINE_UP_METHOD == 2) {
			
		}
	}


	/**
	 * START LIAM'S LINEUP Method
	 */
	//Line Up Variables
	static int MAX = 0;
	static int MAX_SPEED = 0;
	static int firstMeasure = 0;

	private static void lineUpWithAdjust() {
		System.out.println("State 1");
        int[] ranges = getRanges(MainRobot.leftRangeFinder, MainRobot.rightRangeFinder);

        //
        if (firstMeasure == 0){
                firstMeasure = 1;
                System.out.println("setting max to" + ranges[2]);
                MAX = ranges[2];
        }
        if(ranges[0] > ranges[1]){
                MainRobot.setLeftMotors(getSpeedFromAngle(ranges[2]));
                MainRobot.setRightMotors(Util.inverse(getSpeedFromAngle(ranges[2])));
        }
        if(ranges[1] > ranges[0]){
                MainRobot.setLeftMotors(Util.inverse(getSpeedFromAngle(ranges[2])));
                MainRobot.setRightMotors(getSpeedFromAngle(ranges[2]));
        }
        if(rangeComp(ranges[2], -10, 10)){
                System.out.println("equal ranges");
                MainRobot.setLeftMotors(0);
                MainRobot.setRightMotors(0);
                MainRobot.state = States.TELEOP_MANUAL_DRIVE;
        }
	}

	private static double getSpeedFromAngle(int increment){
		double speed = 1;
		if(increment == MAX){
			//NEED REAL TESTS-------------------------------------------------------------------
			MAX_SPEED = 1;
			return MAX_SPEED;
		}
		//10 steps of speed
		//first step
		System.out.println("increment: " + increment);
		if(increment<=MAX && increment>=((int)((MAX/10)*9))){
			System.out.println("setting speed to: 1");
			speed = 1;
		}if(increment<=((int)(MAX/10)*9) &&increment>=((int)((MAX/10)*8))){
			System.out.println("setting speed to: " + (speed / 10) * 9);
			speed = (speed / 10) * 9;
		}if(increment<=((int)(MAX/10)*8) &&increment>=((int)((MAX/10)*7))){
			System.out.println("setting speed to: " + (speed / 10) * 8);
			speed = (speed / 10) * 8;
		}if(increment<=((int)(MAX/10)*7) &&increment>=((int)((MAX/10)*6))){
			System.out.println("setting speed to: " + (speed / 10) * 7);
			speed = (speed / 10) * 7;
		}if(increment<=((int)(MAX/10)*6) &&increment>=((int)((MAX/10)*5))){
			System.out.println("setting speed to: " + (speed / 10) * 6);
			speed = (speed / 10) * 6;
		}if(increment<=((int)(MAX/10)*5) &&increment>=((int)((MAX/10)*4))){
			System.out.println("setting speed to: " + (speed / 10) * 5);
			speed = (speed / 10) * 5;
		}if(increment<=((int)(MAX/10)*4) &&increment>=((int)((MAX/10)*3))){
			System.out.println("setting speed to: " + (speed / 10) * 4);
			speed = (speed / 10) * 4;
		}if(increment<=((int)(MAX/10)*3) &&increment>=((int)((MAX/10)*2))){
			System.out.println("setting speed to: " + (speed / 10) * 3);
			speed = (speed / 10) * 3;
		}if(increment<=((int)(MAX/10)*2) &&increment>=((int)((MAX/10)*1))){
			System.out.println("setting speed to: " + (speed / 10) * 2);
			speed = (speed / 10) * 2;
		}if(increment<=((int)(MAX/10)*1) &&increment>=((int)((MAX/10)*0))){
			System.out.println("setting speed to: " + (speed / 10) * 1);
			speed = (speed / 10) * 1;
		}

		System.out.println("speed: " + speed);
		return speed;
	}

	private static int[] getRanges(AnalogChannel rangeFinderL, AnalogChannel rangeFinderR){

		double distanceL1 = Util.getRangeFinderDistance(rangeFinderL)*10;
		double distanceR1 = Util.getRangeFinderDistance(rangeFinderR)*10;
		int distanceL = (int) distanceL1;
		int distanceR = (int) distanceR1;
		System.out.println(distanceL);
		System.out.println(distanceR);
		int increment = 0;
		if(distanceL>distanceR){
			increment = distanceL - distanceR;
		}
		if(distanceR>distanceL){
			increment = distanceR - distanceL;
		}
		if(distanceR == distanceL){
			increment = -1;
		}
		int[] ranges = {distanceL, distanceR, increment};
		return ranges;
	}

	private static boolean rangeComp(int check, int start, int end){
		boolean isInRange = false;
		for(int y = start; y < end; y++){
			if(check == y){
				isInRange = true;
				break;
			}
		}
		return isInRange;
	}
	
	/**
	 * End Liam's Line Up Method
	 */


}
