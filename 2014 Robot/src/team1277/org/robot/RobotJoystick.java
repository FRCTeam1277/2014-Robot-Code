package team1277.org.robot;


import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;

public class RobotJoystick {
	private Joystick stick;
	private Hand hand;
	private RobotJoystick() {}
	public boolean isXBox = false;
	public RobotJoystick(int port) {
		stick = new Joystick(port);
		this.hand = Hand.kLeft;
	}
	
	public RobotJoystick(int port, Hand hand) {
		stick = new Joystick(port);
		this.hand = hand;
	}
	public RobotJoystick(Joystick joy, Hand hand, boolean xbox) {
		stick = joy;
        this.hand=hand;
        this.isXBox = xbox;
	}
	
	public double getX() {
		double m = 1;
		if (isXBox) {
			m=-1;
		}
		if (hand.equals(Hand.kLeft)) {
			return m*stick.getRawAxis(1);
		}
		else {
			return m*stick.getRawAxis(4);
		}
	}
	
	public double getY() {
		if (hand.equals(Hand.kLeft)) {
			return stick.getRawAxis(2);
		}
		else {
			return stick.getRawAxis(5);
		}
	}
	
	public double getZ() {
		return stick.getRawAxis(3);
	}
	
	public double getThrottle() {
		return stick.getRawAxis(4);
	}
	
	public boolean getRawButton(int button) {
		return stick.getRawButton(button);
	}
	
	public double getRawAxis(int axis) {
		return stick.getRawAxis(axis);
	}
	
	
}
