package team1277.org.robot;

public class ManualMethods {
	private final static int TANK_DRIVE_METHOD = 1;
	private final static int ARCADE_DRIVE_METHOD = 1;

	public final static int DRIVE_MODE_TANK = 1;
	public final static int DRIVE_MODE_ARCADE = 2;
	public static int driveMode = 1;

	public static void driveChain() {
		if (driveMode == DRIVE_MODE_TANK) {
			tankDrive();
		}
		else if (driveMode == DRIVE_MODE_ARCADE) {
			arcadeDrive();
		}
	}

	public static void tankDrive() {

		//Liams Tanks Drive
		if (TANK_DRIVE_METHOD == 1) {
			double speed = 1;
			double leftCoord = MainRobot.leftJoyStick.getY();
			double rightCoord = MainRobot.rightJoyStick.getY();

			MainRobot.leftMotors.set(leftCoord*speed);
			MainRobot.rightMotors.set(rightCoord*speed);
		}
	}

	@SuppressWarnings("unused")
	public static void arcadeDrive() {
		if (ARCADE_DRIVE_METHOD == 1) {
			adjustingArcadeDrive();
		}
	}



	/**
	 * Start Liam's Arcade Drive
	 */

	private static void adjustingArcadeDrive() {
		double leftX = MainRobot.leftJoyStick.getX();
		double leftY = MainRobot.leftJoyStick.getY();
		//System.out.println("X CORD: " + leftX);
		//System.out.println("Y CORD: " + leftY);
		if(leftY >= 0){
			//top

			if(leftX == 0){
				//forward
				MainRobot.leftMotors.set(leftY);
				MainRobot.rightMotors.set(leftY);
			}
			if(leftY == 0){
				//right
				MainRobot.leftMotors.set(leftX);
				MainRobot.rightMotors.set(Util.inverse(leftX));
			}
			if(leftX != 0 && leftY != 0){
				//combo
				if(leftX > 0){
					MainRobot.leftMotors.set(leftY);
					MainRobot.rightMotors.set(leftX / 2);
				}
				if(leftX < 0){
					MainRobot.leftMotors.set(leftY / 2);
					MainRobot.rightMotors.set(Math.abs(leftX));
				}
			}
		}
		if(leftY <= 0){
			//bot

			if(leftX == 0){
				//backward
				MainRobot.leftMotors.set(leftY);
				MainRobot.rightMotors.set(leftY);
			}
			if(leftY == 0){
				//right
				MainRobot.leftMotors.set(leftX);
				MainRobot.rightMotors.set(Util.inverse(leftX));
			}
			if(leftX != 0 && leftY != 0){
				//combo
				if(leftX > 0){
					MainRobot.leftMotors.set(leftY);
					MainRobot.rightMotors.set(Util.inverse(leftX / 2));
				}
				if(leftX < 0){
					MainRobot.leftMotors.set(leftY / 2);
					MainRobot.rightMotors.set(leftX);
				}



			}
		}

	}
	
	/**
	 * End Liam's Arcade Drive
	 */
}
