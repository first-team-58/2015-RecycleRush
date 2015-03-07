package org.usfirst.frc.team58.robot;

import edu.wpi.first.wpilibj.Timer;

public class Auto {

	private static  Timer time = new Timer();
	public static void Init(){
		time.start();
		Drive.reset();
	}
	public static void run(int program){
		switch(program){
			case 1:
				reset();
				break;
			case 2:
				forward();
				break;
			case 3:
				container();
				break;
			case 4:
				step();
				break;
			default:
				noop();
				break;
		}
	}
	private static void noop(){
		Arm.SetArm(0);
		Drive.drive(0, 0, 0);
	}
	private static void reset(){
		Arm.SetArm(-.5);
		Drive.drive(0, 0, 0);
		Arm.SetCollector(0);
	}

	private static void forward(){
		if (time.get()<1.5){
			Drive.drive(0.5, 0, 0);
		}
	}
		
	public static void container() {
		double now = time.get();
		if (now < 3){
			Arm.SetArm(0.5);
		}else {
			Arm.SetArm(0);
			double angle = Drive.getGyro();
			if (now < 4.75){
				Drive.driveCartesian(0, .4, 0);
			}else if (angle > -85) {
				Drive.driveCartesian(0, 0, -.25);
			}else {
				Drive.driveCartesian(0, 0, 0);
			}
		}
	}
	
	public static void step(){
		double now = time.get();
		if (Arm.GetAngle()<1.57 && now < 4.5){
			Arm.SetArm(0.5);
			Arm.SetCollector(0);
			Drive.driveCartesian(0, 0, 0);
		} else {
			if (now < 4.5) {
				Arm.SetArm(0);
				Arm.SetCollector(0);
				Drive.drive(0, 0, 0);
			} else if(now < 7){
				Arm.SetArm(0);
				Arm.SetCollector(-0.5);
				Drive.drive(0, 0, 0);
			} else if (now < 7.5){
				Arm.SetArm(-0.5);
				Arm.SetCollector(0);
				Drive.drive(0, 0, 0);	
			} else if (now < 8){
				Arm.SetArm(0);
				Arm.SetCollector(0.3);
				Drive.drive(0, 0, 0);				
			} else if (now < 10.5) {
				Arm.SetArm(1);
				Arm.SetCollector(0.5);
				Drive.drive(.25, 180, 0);
			} else if (now < 12) {
				Arm.SetArm(1);
				Arm.SetCollector(0.5);
				Drive.drive(0, 180, 0);
			} else {
				Arm.SetCollector(0);
				Arm.SetArm(0);
				Drive.driveCartesian(0, 0, 0);
			}
		}
	}
}
