import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class Launcher {
	
	private static final double SIDE_TARGET_ANGLE = 18.43;
	private static final int MOTOR_ACCELERATION = 4000;
	private static final int MOTOR_SPEED = 700;
	private static final int MOTOR_ROTATION_ANGLE = 90;
	
	public enum Target {
		LeftTarget,
		CenterTarget,
		RightTarget,
		NoTarget // For completeness
	}
	
	private EV3MediumRegulatedMotor motor;
	private Target target;
	private Navigation navigation;
	
	/**
	 * Constructor
	 * @param motor The motor used for launching the ball.
	 * @param navigation The navigation class used for aiming the robot.
	 * @param target The target that the launcher is aiming for.
	 */
	public Launcher(EV3MediumRegulatedMotor motor, Navigation navigation, Target target) {
		this.motor = motor;
		this.target = target;
		this.navigation = navigation;
		
		this.motor.setAcceleration(MOTOR_ACCELERATION);
		this.motor.setSpeed(MOTOR_SPEED);
	}
	
	/**
	 * Launch the ball.
	 */
	public void launch() {
		turnTo(target);
		motor.rotate(MOTOR_ROTATION_ANGLE);
	}
	
	/**
	 * Turns the robot facing the desired target.
	 * @param target The target that we are aiming for.
	 */
	private void turnTo(Target target) {
		switch (target) {
		case LeftTarget:
			navigation.turnTo(-SIDE_TARGET_ANGLE, true);
			break;
			
		case RightTarget:
			navigation.turnTo(SIDE_TARGET_ANGLE, true);
			break;
			
		default:
			break;
		}
	}
}
