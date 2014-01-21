package team1277.org.robot;

public class Gyro {
	public static double angle = 0;
	
	public static void setAngle(double a) {
		angle = a;
	}
	
	public static double getAngle() {
		return angle;
	}
	
	public static void updateAngle(double time) {
		double val = MainRobot.gyro.getVoltage();
		val -= 2.5;
		double change = val/7000d;
		angle += change * time;
		angle %= 360;
	}
}
