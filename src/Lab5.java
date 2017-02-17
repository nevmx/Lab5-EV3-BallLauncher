import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;

public class Lab5 {

	// Static Resources:
	// Left motor connected to output A
	// Right motor connected to output D
	// Ultrasonic sensor port connected to input S1
	// Color sensor port connected to input S2
	private static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));
	private static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	private static final EV3LargeRegulatedMotor launcherMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("B"));
	
	public static void main(String[] args) {
		// setup the odometer and display
		Odometer odo = new Odometer(leftMotor, rightMotor, 30, true);
		Navigation nav = new Navigation(odo);
		
		int buttonChoice;
		final TextLCD t = LocalEV3.get().getTextLCD();
		
		// Read user's choice for target
		do {
			// clear the display
			t.clear();

			// ask the user whether the motors should drive in a square or float
			t.drawString("       ^        ", 0, 0);
			t.drawString("_____Middle_____", 0, 1);
			t.drawString(" <     |      > ", 0, 2);
			t.drawString("  Left | Right  ", 0, 3);
			t.drawString("       |        ", 0, 4);

			buttonChoice = Button.waitForAnyPress();
		} while (buttonChoice != Button.ID_LEFT
				&& buttonChoice != Button.ID_RIGHT
				&& buttonChoice != Button.ID_UP);
		
		t.drawString("                ", 0, 0);
		t.drawString("                ", 0, 1);
		t.drawString(" Press ENTER to ", 0, 2);
		t.drawString("   launch again ", 0, 3);
		t.drawString("                ", 0, 4);
		
		// The target we are aiming for.
		Launcher.Target target;
		
		// The target field of the Launcher class must be set according to the user's
		// desired target.
		switch (buttonChoice) {
		case Button.ID_LEFT:
			target = Launcher.Target.LeftTarget;
			break;
			
		case Button.ID_UP:
			target = Launcher.Target.CenterTarget;
			break;
			
		case Button.ID_RIGHT:
			target = Launcher.Target.RightTarget;
			break;
			
		default:
			target = Launcher.Target.NoTarget;
			System.exit(0);
			break;
		}
		
		// Create the Launcher and begin launching balls
		Launcher launcher = new Launcher(launcherMotor, nav, target);
		launcher.launch();
		
		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);	
		
	}

}
