package team1277.org.robot;

public class ArcadeMode {
	public static void arcadeDrive() {
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
}