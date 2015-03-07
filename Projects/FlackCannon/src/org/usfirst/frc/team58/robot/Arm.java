package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	
	//arm Talons
	private static Talon ArmLeft = new Talon(6);
	private static Talon ArmRight = new Talon(7);
	
	private static Talon collector = new Talon(8);
	
	private static DigitalInput LimitUp = new DigitalInput(1);
	private static DigitalInput LimitDown = new DigitalInput(0);
	
	private static AnalogInput angle = new AnalogInput(1); 
	
	public static void init() {
		LiveWindow.addActuator("Arm", "Left", ArmLeft);
		LiveWindow.addActuator("Arm", "Right", ArmRight);
		LiveWindow.addSensor("Arm", "Limit Up", LimitUp);
		LiveWindow.addSensor("Arm", "Limit Down", LimitDown);
		LiveWindow.addSensor("Arm", "Angle", angle);
    }
	public static void doStats(){
		// Only put smart dashboard stuff here
		// Put debug stuff on the dashboard
		SmartDashboard.putBoolean("LimitUp", LimitUp.get());
		SmartDashboard.putBoolean("LimitDown", LimitDown.get());
	}
	
	public static void SetArm(double speed) {
		// Make sure motors go at arm speed
		if ((speed > 0 && !LimitUp.get()) || (speed < 0 && !LimitDown.get())){
			speed = 0;
		}
		ArmLeft.set(speed);
		ArmRight.set(speed);
		
	}
	
	public static void SetCollector(double collectorSpeed){
		//collector speed
				collector.set(collectorSpeed);
	}
	
	public static void DoTeleop(){
		double speed = 0;
		double collectorSpeed = 0;
		boolean fast = Joysticks.operator.getRawButton(5);
		
		double pad = Joysticks.operator.getPOV(0);
		
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
		
		//control collector
		if(pad > 0 && pad < 180){
			collectorSpeed = 0.5;
		} else if(pad < 0 && pad > 180){
			collectorSpeed = -0.5;
		} else if(pad == -1){
			collectorSpeed = 0;
		}
		
		//control collector
		if(Joysticks.operator.getRawButton(1)){
			collectorSpeed = 0.5;
		} else if(Joysticks.operator.getRawButton(2)){
			collectorSpeed = -0.5;
		} else if(pad == -1){
			collectorSpeed = 0;
		}
		
		SetArm(speed);
		SetCollector(collectorSpeed);
	}
	
	public static double GetAngle(){
		return angle.getAverageVoltage();
	}
}