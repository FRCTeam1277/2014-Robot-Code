package team1277.org.robot;

import edu.wpi.first.wpilibj.AnalogChannel;

public class Util {
	public static double getRangeFinderDistance(AnalogChannel rangeFinder) {
		final double vcc = 5.0;
		final double vi = vcc/512d;
		return rangeFinder.getVoltage()/vi;
		
	}
	
	public static double inverse(double x){
        double finishedNumber;                
        finishedNumber = x*-1;
        return finishedNumber;
	}
}
