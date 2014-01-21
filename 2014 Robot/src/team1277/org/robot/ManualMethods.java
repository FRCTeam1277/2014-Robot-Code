package team1277.org.robot;


public class ManualMethods {
	private final static int TANK_DRIVE_METHOD = 1;
	private final static int ARCADE_DRIVE_METHOD = 2;

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
        
        public static void relitiveDrive() {
            double y = MainRobot.rightJoyStick.getY();
            double x = MainRobot.rightJoyStick.getX();
            //double ang = Math.
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
				MainRobot.setLeftMotors(leftY);
				MainRobot.setRightMotors(leftY);
			}
			if(leftY == 0){
				//right
				MainRobot.setLeftMotors(leftX);
				MainRobot.setRightMotors(Util.inverse(leftX));
			}
			if(leftX != 0 && leftY != 0){
				//combo
				if(leftX > 0){
					MainRobot.setLeftMotors(leftY);
					MainRobot.setRightMotors(leftX / 2);
				}
				if(leftX < 0){
					MainRobot.setLeftMotors(leftY / 2);
					MainRobot.setRightMotors(Math.abs(leftX));
				}
			}
		}
		if(leftY <= 0){
			//bot

			if(leftX == 0){
				//backward
				MainRobot.setLeftMotors(leftY);
				MainRobot.setRightMotors(leftY);
			}
			if(leftY == 0){
				//right
				MainRobot.setLeftMotors(leftX);
				MainRobot.setRightMotors(Util.inverse(leftX));
			}
			if(leftX != 0 && leftY != 0){
				//combo
				if(leftX > 0){
					MainRobot.setLeftMotors(leftY);
					MainRobot.setRightMotors(Util.inverse(leftX / 2));
				}
				if(leftX < 0){
					MainRobot.setLeftMotors(leftY / 2);
					MainRobot.setRightMotors(leftX);
				}



			}
		}

	}
	
	/**
	 * End Liam's Arcade Drive
	 */
}
