package team1277.org.robot;

public class Gyro {
	public static double angle = 0;
        private static double initVolt = 0;
        
        public static void init() {
            initVolt = MainRobot.gyro.getVoltage();
            angle = 0;
        }
	
	public static void setAngle(double a) {
		angle = a;
	}
	
	public static double getAngle() {
		return angle;
	}
	
	public static void updateAngle(double time) {
		double val = MainRobot.gyro.getVoltage();
		val -= initVolt;
		double change = val/.007d;
		angle += change * time;
                if (angle>360)
                    angle-=360;
		if (angle <0)
                    angle+=360;
	}
}
