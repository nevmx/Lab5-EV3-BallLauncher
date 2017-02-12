import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;

public class Lab4 {

	// Static Resources:
	// Left motor connected to output A
	// Right motor connected to output D
	// Ultrasonic sensor port connected to input S1
	// Color sensor port connected to input S2
	private static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));
	private static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	
	public static void main(String[] args) {
		// setup the odometer and display
		Odometer odo = new Odometer(leftMotor, rightMotor, 30, true);
		LCDInfo lcd = new LCDInfo(odo);
		
		Button.waitForAnyPress();
		
		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);	
		
	}

}
