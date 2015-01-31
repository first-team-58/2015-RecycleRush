package org.usfirst.frc.team8855.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	
	//collecter intake Talons
	private static Talon  IntakeLeft= new Talon(4);
	private static Talon IntakeRight = new Talon(5);
	
	//arm Talons
	private static Talon ArmLeft = new Talon(6);
	private static Talon ArmRight = new Talon(7);
	
	private static DigitalInput LimitUp = new DigitalInput(0);
	private static DigitalInput LimitDown = new DigitalInput(1);
	
	public static void doStats(){
		// Only put smart dashboard stuff here
		// Put debug stuff on the dashboard
		SmartDashboard.putBoolean("LimitUp", LimitUp.get());
		SmartDashboard.putBoolean("LimitDown", LimitDown.get());
	}
	
	private static void SetArm(double speed) {
		// Make sure motors go at arm speed
		ArmLeft.set(speed);
		ArmRight.set(speed);
	}
	
	private static void SetCollector (double speed) {
		IntakeLeft.set(speed);
		IntakeRight.set(speed);
	}
	
	public static void DoTeleop(){
		//speed of arm translation
		double speed = 0;
		//LB is slow button for arm translation
		boolean slow = Joysticks.driver.getRawButton(5);
		
		if (Joysticks.operator.getRawButton(4) && LimitUp.get() && slow){
			// Go up half-speed if Y is pressed and limit switch not triggered and slow 
			speed = 0.25;
		} else if (Joysticks.operator.getRawButton(4) && LimitUp.get()){
			// Go up normal speed if Y is pressed and limit switch not triggered
			speed = 0.5;
		} else if(Joysticks.operator.getRawButton(3) && LimitDown.get() && slow){ 
			// Go down half-speed if X is pressed and limit switch not triggered and slow
			speed = -0.25;
		} else if(Joysticks.operator.getRawButton(3) && LimitDown.get()){
			// Go down normal speed if X is pressed and limit switch not triggered
			speed = -0.5;
		} else {
			speed = 0;
		}
		
		SetArm(speed);
		
		SetCollector(Joysticks.operator.getZ());
	}
}