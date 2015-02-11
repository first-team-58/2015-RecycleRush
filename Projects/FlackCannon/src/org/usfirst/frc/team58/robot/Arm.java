package org.usfirst.frc.team58.robot;

import org.usfirst.frc.team58.robot.Joysticks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	
	//arm Talons
	private static Talon ArmLeft = new Talon(6);
	private static Talon ArmRight = new Talon(7);
	
	private static DigitalInput LimitUp = new DigitalInput(0);
	private static DigitalInput LimitDown = new DigitalInput(1);
	
	public static void init() {
		LiveWindow.addActuator("Arm", "Left", ArmLeft);
		LiveWindow.addActuator("Arm", "Right", ArmRight);
		LiveWindow.addSensor("Arm", "Limit Up", LimitUp);
		LiveWindow.addSensor("Arm", "Limit Down", LimitDown);
    }
	public static void doStats(){
		// Only put smart dashboard stuff here
		// Put debug stuff on the dashboard
		SmartDashboard.putBoolean("LimitUp", LimitUp.get());
		SmartDashboard.putBoolean("LimitDown", LimitDown.get());
	}
	
	public static void SetArm(double speed) {
		// Make sure motors go at arm speed
		ArmLeft.set(speed);
		ArmRight.set(speed);
	}
		
	public static void DoTeleop(){
		double speed = 0;
		boolean fast = Joysticks.operator.getRawButton(5);
		
		if (Joysticks.operator.getRawButton(4) && LimitUp.get()){
			// Go up if Y is pressed and limit switch not triggered
			speed = 0.5;
		} else if(Joysticks.operator.getRawButton(3) && LimitDown.get()){ 
			// Go down if X is pressed and limit switch not triggered
			speed = -0.5;
		}
		
		//check for active slow button in up or down is pressed
		if(fast){
			//double the speed
			speed *= 2;
		}
		
		SetArm(speed);
		
	}
}