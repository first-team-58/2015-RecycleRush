package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
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
	private static AnalogInput IR = new AnalogInput(2);
	
	public static void init() {
		LiveWindow.addActuator("Arm", "Left", ArmLeft);
		LiveWindow.addActuator("Arm", "Right", ArmRight);
		LiveWindow.addSensor("Arm", "Limit Up", LimitUp);
		LiveWindow.addSensor("Arm", "Limit Down", LimitDown);
		LiveWindow.addSensor("Arm", "Angle", angle);
		LiveWindow.addSensor("Arm", "IR Sensor", IR);
    }
	public static void doStats(){
		// Only put smart dashboard stuff here
		// Put debug stuff on the dashboard
		SmartDashboard.putBoolean("LimitUp", LimitUp.get());
		SmartDashboard.putBoolean("LimitDown", LimitDown.get());
	}
	
	public static void SetArm(double speed) {
		// Make sure motors go at arm speed
		if ((speed > 0 && !LimitUp.get()) || (speed < 0 && !LimitDown.get())){ //GODDARD check down is false
			speed = 0;
		}
		ArmLeft.set(speed);
		ArmRight.set(speed);
		
	}
	
	public static void SetCollector(double collectorSpeed){
		//collector speed
				collector.set(collectorSpeed);
	}
	
	
	private static boolean stage2;
	private static boolean ready = true;
	
	public static void GoAngle(double target){
		
		double now = angle.getAverageVoltage();
		double diff = target - now;
		double speed = 0;
		if (diff < 0){
			//down
			if (diff < -0.1){
				speed = -0.5;
			}else if (diff < -0.01){
				speed = -0.3;
			}
		}else {
			//up
			if (diff > 0.1){
				speed = 0.5;
			}else if (diff > 0.01){
				speed = 0.3;
			}
		}
		SetArm(speed);
	}
	
	public static void GoIR(double target){
        double now = IR.getAverageVoltage();
        double diff = target - now;
        double speed = 0;
        boolean reverse = diff > 0;
        diff = Math.abs(diff);
        
        if (diff > 0.2){
            speed = 0.5;
        }else if (diff > 0.02){
            speed = 0.25;
        }
        speed *= reverse? -1 : 1;
        SetArm(speed);
    } 
	
	public static void DoTeleop(){
		double speed = 0;
		double collectorSpeed = 0;
		boolean fast = Joysticks.operator.getRawButton(5);
		
		double pad = Joysticks.operator.getPOV(0);
		
		if (Joysticks.operator.getRawButton(4)){
			// Go up if Y is pressed and limit switch not triggered
			speed = 0.5;
		} else if(Joysticks.operator.getRawButton(3)){ 
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
		
		//tote shute angle button
		if (Joysticks.operator.getRawButton(6)){
			//GoAngle(2.11); //angle
			GoIR(1.48); //IR
		}else {
			ready = true;
			stage2 = false;
			SetArm(speed);
		}
		SetCollector(collectorSpeed);
		
		
		if(Joysticks.operator.getPOV(0) == 0){
			GoIR(2.035);
		}
	}
	
	public static double GetAngle(){
		return angle.getAverageVoltage();
	}
}