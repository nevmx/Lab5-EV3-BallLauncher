import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Launcher {
	
	private static final double SIDE_TARGET_ANGLE = 18.43;
	private static final int MOTOR_ACCELERATION = 4000;
	private static final int MOTOR_SPEED_LAUNCH = 1000;
	private static final int MOTOR_SPEED_COMEBACK = 100;
	private static final int MOTOR_ROTATION_ANGLE = 120;
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
		sleep(1500);
		
		// Turn the robot facing the target.
		turnTo(target);
		
		int buttonPress;
		
		do {
			sleep(2000);
			
			// Launch the ball.
			motor.rotate(MOTOR_ROTATION_ANGLE);
			
			sleep(1500);
			
			motor.setSpeed(MOTOR_SPEED_COMEBACK);
			motor.rotateTo((int)this.initialMotorPosition);
			motor.flt();
			motor.setSpeed(MOTOR_SPEED_LAUNCH);
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
