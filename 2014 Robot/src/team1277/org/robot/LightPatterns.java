package team1277.org.robot;

import edu.wpi.first.wpilibj.Relay.Value;

public class LightPatterns {
	static int time = 0;
	static int maxTime = 1000;
	
	public static void update(int delta) {
		time += delta;
		if (time>maxTime) {
			time %=maxTime;
		}
		maxTime = (int) (300+700-700*Math.abs(MainRobot.rightJoyStick.getY()));
		if (time>maxTime/2) {
			MainRobot.frontLight.set(Value.kOn);
			MainRobot.backLight.set(Value.kOn);
		}
		else {
			MainRobot.frontLight.set(Value.kOff);
			MainRobot.backLight.set(Value.kOff);
		}
		
	}
}
