import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class Launcher {
	
	private final static double SIDE_TARGET_ANGLE = 18.43;
	
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
	}
	
	/**
	 * Launch the ball.
	 */
	public void launch() {
		turnTo(target);
	}
	
	/**
	 * Turns the robot facing the desired target.
	 * @param target The target that we are aiming for.
	 */
	private void turnTo(Target target) {
		switch (target) {
		case LeftTarget:
			navigation.turnTo(-this.SIDE_TARGET_ANGLE, true);
			break;
			
		case RightTarget:
			navigation.turnTo(this.SIDE_TARGET_ANGLE, true);
			break;
			
		default:
			break;
		}
	}
}
