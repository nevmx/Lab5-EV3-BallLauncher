import lejos.hardware.motor.EV3MediumRegulatedMotor;

public class Launcher {
	
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
		
	}
}
