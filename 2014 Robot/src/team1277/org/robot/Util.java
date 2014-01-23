package team1277.org.robot;

public class ArcadeMode {
	public static void arcadeDrive(){
	double leftX = MainRobot.leftJoyStick.getX();
	double leftY = MainRobot.leftJoyStick.getY();
	
	double slopetemp = 0;
	
	Double slope = (double) 0;
	
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
			MainRobot.leftMotor1.set(leftX + leftY);
			MainRobot.rightMotor2.set(leftX + leftY);
			return;
		}
		if(leftY<0){
			//System.out.println("BACKWARD----------------------------");
			MainRobot.leftMotor1.set(leftX + leftY);
			MainRobot.rightMotor2.set(leftX + leftY);
			return;
		}
		if(leftX>0){
			//System.out.println("RIGHT----------------------------");
			MainRobot.leftMotor1.set(leftX);
			MainRobot.rightMotor2.set(leftX * -1);
			return;
		}
		if(leftX<0){
			//System.out.println("LEFT----------------------------");
			MainRobot.leftMotor1.set(leftX* -1);
			MainRobot.rightMotor2.set(leftX);
			return;
		}
		
		if(leftY == 0){
			MainRobot.leftMotor1.set(0);
			MainRobot.rightMotor2.set(0);
			return;
		}
	}
	
	//RIGHT
	if(leftX>0){
	if(intSlope>150){
		//System.out.println("Up Up Right");
			MainRobot.leftMotor1.set((leftX + leftY)/2);
			MainRobot.rightMotor2.set((leftX + leftY)/2);
			return;
	}
	if(intSlope>=50 && intSlope <= 150){
		//System.out.println("Up Right");
			MainRobot.leftMotor1.set((leftX + leftY)/2);
			MainRobot.rightMotor2.set((leftX + leftY)/4);
			return;
	}
	if(intSlope>=-50 && intSlope < 50){
		
		//System.out.println("Straight Right");
			MainRobot.leftMotor1.set((leftX + leftY));
			MainRobot.rightMotor2.set(((leftX + leftY))*-1);
			return;
	}
	
	if(intSlope>=-150 && intSlope < -50){
		//System.out.println("Bottom Right:::LEFT X: " + leftX + " LEFT Y " + leftY);
			
			MainRobot.leftMotor1.set(((Math.abs(leftX) + Math.abs(leftY))/2) * -1);
			
			MainRobot.rightMotor2.set(((Math.abs(leftX) + Math.abs(leftY))/4)* -1);
			
			return;
	}
	
	if(intSlope<-150){
		//System.out.println("Bottom Bottom Right");
			MainRobot.leftMotor1.set(leftY);
			MainRobot.rightMotor2.set(leftY);
			return;
	}
	}
		//LEFT
	if(leftX<0){
		if(intSlope>150){
			//System.out.println("Up Up Left");
			MainRobot.leftMotor1.set((leftX + leftY)/2);
			MainRobot.rightMotor2.set((leftX + leftY)/2);
			return;
		}
		if(intSlope>=50 && intSlope <= 150){
			//System.out.println("Up Left");
			MainRobot.leftMotor1.set((Math.abs(leftX) + Math.abs(leftY))/4);
			MainRobot.rightMotor2.set((Math.abs(leftX) + Math.abs(leftY))/2);
			return;
			
		}
		if(intSlope>=-50 && intSlope < 50){
			//System.out.println("Straight Left");
			MainRobot.leftMotor1.set((leftX + leftY)*-1);
			MainRobot.rightMotor2.set((leftX + leftY));
			return;
		}
		
		if(intSlope>=-150 && intSlope < -50){
			//System.out.println("Bottom Left");
			MainRobot.leftMotor1.set(((leftX + leftY)/4));
			MainRobot.rightMotor2.set(((leftX + leftY)/2));
			return;
		}
		
		if(intSlope<-150){
			//System.out.println("Bottom Bottom Left");
			MainRobot.leftMotor1.set(leftY);
			MainRobot.rightMotor2.set(leftY);
			return;
			}
		}
	}
}
