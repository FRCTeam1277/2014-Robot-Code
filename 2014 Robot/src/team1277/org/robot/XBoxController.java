package team1277.org.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class XBoxController extends Joystick {

	public Hand hand;
	
	public XBoxController(int port, Hand hand) {
		super(port);
		this.hand = hand;
		
	}
	
	
	
	

}
