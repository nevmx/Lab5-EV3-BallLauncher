import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Launcher {
	
	private static final double SIDE_TARGET_ANGLE = 20;
	private static final int MOTOR_ACCELERATION = 1000;
	private static final int MOTOR_SPEED_LAUNCH = 400;
	private static final int MOTOR_SPEED_COMEBACK = 50;
	private static final int MOTOR_ROTATION_ANGLE = 240;
	private static final double INITIAL_THETA = 90.0;
	
	public enum Target {
		LeftTarget,
		CenterTarget,
		RightTarget,
		NoTarget // For completeness
	}
	
	private EV3LargeRegulatedMotor motor;
	private Target target;
	private Navigation navigation;
	private float initialMotorPosition;
		
	/**
	 * Constructor
	 * @param launchermotor The motor used for launching the ball.
	 * @param navigation The navigation class used for aiming the robot.
	 * @param target The target that the launcher is aiming for.
	 */
	public Launcher(EV3LargeRegulatedMotor launchermotor, Navigation navigation, Target target) {
		this.motor = launchermotor;
		this.target = target;
		this.navigation = navigation;
		
		this.motor.setAcceleration(MOTOR_ACCELERATION);
		this.motor.setSpeed(MOTOR_SPEED_LAUNCH);
		this.initialMotorPosition = this.motor.getPosition();
	}
	
	/**
	 * Launch the ball.
	 */
	public void launch() {
		// Allow the operator to remove his or her hand before turning the bot
		sleep(1500);
		
		// Turn the robot facing the target.
		turnTo(target);
		
		int buttonPress;
		
		do {
			// Allow the operator to remove his or her hand before launching the ball
			sleep(2000);
			
			// Launch the ball.
			motor.rotate(MOTOR_ROTATION_ANGLE);
			
			// Let the system stabilize before returning the arm
			sleep(1500);
			
			// Return the arm to initial position
			motor.setSpeed(MOTOR_SPEED_COMEBACK);
			motor.rotateTo((int)this.initialMotorPosition);
			
			// Let the arm rest on the stopper or ground
			motor.flt();
			motor.setSpeed(MOTOR_SPEED_LAUNCH);
			
			// Get user input to launch ball again
			buttonPress = Button.waitForAnyPress();
			
		} while (buttonPress == Button.ID_ENTER);
	}
	
	/**
	 * Turns the robot facing the desired target.
	 * @param target The target that we are aiming for.
	 */
	private void turnTo(Target target) {
		switch (target) {
		case LeftTarget:
			navigation.turnTo(INITIAL_THETA - SIDE_TARGET_ANGLE, true);
			break;
			
		case RightTarget:
			navigation.turnTo(INITIAL_THETA + SIDE_TARGET_ANGLE, true);
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * Sleeps the thread.
	 * @param timeout The time to sleep for.
	 */
	private void sleep(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
