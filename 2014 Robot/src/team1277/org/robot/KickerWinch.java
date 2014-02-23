package team1277.org.robot;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

public class KickerWinch {
	public static final int CLUTCH_OPEN = 1;
	public static final int CLUTCH_CLOSED = 2;
	public static final int CLUTCH_OPENEING = 3;
	public static final int CLUTCH_CLOSING = 4;
	
	public static final int WINCH_RETRACTED = 1;
	public static final int WINCH_RELAXED = 2;
	public static final int WINCH_RELAXING = 3;
	public static final int WINCH_RETRACTING = 4;
	
	public static final int BOOT_RETRACTED = 1;
	public static final int BOOT_RELAXED = 2;
	
	public static final int IDLE_STATE = 0;
	public static final int LAUNCH_STATE = 1;
	public static final int RELOAD_RETRACT_STATE = 2;
	public static final int RELOAD_RELAX_STATE = 3;
	public static final int AUTO_RELOAD_STATE1 = 4;
	public static final int AUTO_RELOAD_STATE2 = 5;
	public static final int AUTO_RELOAD_STATE3 = 6;
	public static final int LOWER_BOOT = 7;
	
	public static final int AUTO_RELOAD_MODE = 1;
	public static final int MANUAL_RELOAD_MODE = 2;
	
	public static final int INIT_STATE_RELAXED = 1;
	public static final int INIT_STATE_LOADED = 2;
	
	public static int winchState;
	public static int clutchState;
	public static int bootState;
	
	public static int commandState;
	public static int handleStateChange;
	
	private static final double potVoltRelax = 4.94;
	private static final double potVoltRetract = 4.25;
	
	private static final long retractTime = 53029;
	private static final long relaxTime = 50511;
	
	public static void init(int state){ 
		if (state == INIT_STATE_RELAXED) {
			winchState = WINCH_RELAXED;
			clutchState = CLUTCH_OPEN;
			bootState = BOOT_RELAXED;
		}
		else if (state == INIT_STATE_LOADED){
			winchState = WINCH_RELAXED;
			clutchState = CLUTCH_CLOSED;
			bootState = BOOT_RETRACTED;
		}
		commandState = IDLE_STATE;
	}
	
	public static void onLoop() {
		long delta=0;
		if (lastFrame != -1) {
			delta = System.currentTimeMillis()-lastFrame;
		}
		lastFrame = System.currentTimeMillis();
		elapsed +=delta;
		update();
		processCommand();
	}
	
	public static void launch() {
		if (commandState == IDLE_STATE && bootState == BOOT_RETRACTED) { 
			commandState = LAUNCH_STATE;
		}
	}
	
	public static void reload() {
		if (commandState == IDLE_STATE && bootState == BOOT_RELAXED) {
			commandState = AUTO_RELOAD_STATE2;//RELOAD_RETRACT_STATE;
		}
	}
	
	/*public static void lowerBoot() {
		if (commandState==IDLE_STATE && bootState == BOOT_RETRACTED) {
			commandState = LOWER_BOOT;
		}
	}*/
	private static long lastFrame = -1;
	private static long elapsed;
	private static void update() {
		
		//Update the clutch based on limit switch
		if (clutchState == CLUTCH_CLOSED || clutchState == CLUTCH_OPENEING) {
			if (!MainRobot.clutchOpenLimit.get() && MainRobot.clutchCloseLimit.get()) {
				clutchState = CLUTCH_OPEN;
			}
		}
		else if(clutchState == CLUTCH_OPEN || clutchState == CLUTCH_CLOSING) {
			if (MainRobot.clutchOpenLimit.get() && !MainRobot.clutchCloseLimit.get()) {
				clutchState = CLUTCH_CLOSED;
			}
		}
		
		//Updates the winch based on limit switch and potentiometer
		DriverStationLCD.getInstance().println(Line.kUser5, 1, String.valueOf(elapsed));
		System.out.println(elapsed);
		if (winchState == WINCH_RETRACTING) {
			if (elapsed >= retractTime) {
				winchState = WINCH_RETRACTED;
			//if the winch is retracted, the boot must be retracted
				bootState = BOOT_RETRACTED;
			}
		}
		else if (winchState == WINCH_RELAXING) {
			if (elapsed >= relaxTime) {
				winchState = WINCH_RELAXED;
			}
		}
		//if the winch is relaxed, and the cluch is open, the boot must be relaxed
		if (winchState == WINCH_RELAXED && clutchState == CLUTCH_OPEN) {
			bootState = BOOT_RELAXED;
		}
		
		
		//Opens or closes the clutch
		if (clutchState == CLUTCH_OPENEING) {
			if (bootState == BOOT_RETRACTED) {
				MainRobot.winchRelease.set(1);
			} else {
				MainRobot.winchRelease.set(.5);
			}
		}
		else if (clutchState == CLUTCH_CLOSING) {
			if (bootState == BOOT_RETRACTED) {
				MainRobot.winchRelease.set(-1);
			} else {
				MainRobot.winchRelease.set(-.5);
			}
		}
		else {
			MainRobot.winchRelease.set(0);
		}
		
		//Releases or retracts the winch
		if (winchState == WINCH_RELAXING) {
			MainRobot.winch.set(-1);
		}
		else if (winchState == WINCH_RETRACTING) {
			MainRobot.winch.set(1);
		}
		else  {
			MainRobot.winch.set(0);
		}
	}
	
	private static long lastLaunch=-1;
	private static final int launchCooldown = 1000;
	private static void processCommand() {
		if (commandState == IDLE_STATE) {
			//Idle, either after launching, or after loading
		}
		else if (commandState == AUTO_RELOAD_STATE1) {
			if (System.currentTimeMillis()-lastLaunch>launchCooldown) {
				commandState = AUTO_RELOAD_STATE2;
			}
		}
		else if (commandState == AUTO_RELOAD_STATE2) {
			//auto reloads after a cooldown time
			if (clutchState == CLUTCH_OPEN||clutchState == CLUTCH_OPENEING) {
				clutchState = CLUTCH_CLOSING;
			}
			else if (clutchState == CLUTCH_CLOSED) {
				commandState = AUTO_RELOAD_STATE3;
			}
		}
		else if (commandState == AUTO_RELOAD_STATE3) {
			if (clutchState == CLUTCH_CLOSED||clutchState == CLUTCH_CLOSING) {
				clutchState = CLUTCH_OPENEING;
			}
			else if (clutchState == CLUTCH_OPEN) {
				commandState = RELOAD_RETRACT_STATE;
			}
		}
		else if (commandState == LAUNCH_STATE) {
			//if clutch is closed, tells it to open
			if (clutchState == CLUTCH_CLOSED) {
				clutchState = CLUTCH_OPENEING;
			}
			//when the clutch opens, says the ball has launched
			if (clutchState == CLUTCH_OPEN) {
				if (handleStateChange == AUTO_RELOAD_MODE) {
					commandState = AUTO_RELOAD_STATE1;
					bootState = BOOT_RELAXED;
				}
				else {
					commandState = IDLE_STATE;
				}
				lastLaunch = System.currentTimeMillis();
			}
		}
		else if (commandState == RELOAD_RETRACT_STATE) {
			//if the winch is relaxed, tells it to start retracting
			if (winchState == WINCH_RELAXED) {
				elapsed = 0;
				winchState = WINCH_RETRACTING;
			}
			//if the clutch is closed, tell it to open
			if (clutchState == CLUTCH_CLOSED || clutchState == CLUTCH_CLOSING) {
				clutchState = CLUTCH_OPENEING;
			}
			//if the winch is fully retracted, and the clutch is open, switch to relax reload
			else if (winchState == WINCH_RETRACTED && clutchState == CLUTCH_OPEN) {
				commandState = RELOAD_RELAX_STATE;
			}
		}
		else if (commandState == RELOAD_RELAX_STATE) {
			//if the clutch is open, tell it to close
			if (clutchState == CLUTCH_OPEN) {
				clutchState = CLUTCH_CLOSING;
			}
			//if the the winch is retracted and the clutch is closed, tell the winch to relax
			if (winchState == WINCH_RETRACTED && clutchState == CLUTCH_CLOSED) {
				elapsed = 0;
				winchState = WINCH_RELAXING;
			}
			//if the winch is relaxed, set the state to idle.
			if (winchState == WINCH_RELAXED && clutchState == CLUTCH_CLOSED) {
				commandState = IDLE_STATE;
			}
			
		}
		/*else if (commandState == LOWER_BOOT_RETRACT) {
			
		}*/
	}
}
