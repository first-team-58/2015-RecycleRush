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
			case 5:
				forwardCarpet();
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
		if (time.get()<3){
			Drive.drive(0.5, 0, 0);
		}
	}
	
	private static void forwardCarpet(){
		if (time.get()<2.5){
			Drive.drive(0.5, 0, 0);
		}
	}
	
	private static boolean finished = false;
	public static void container() {
		double now = time.get();
		if (now < 3){
			Arm.SetArm(0.5);
		}else {
			Arm.SetArm(0);
			double angle = Drive.getGyro();
			if (now < 5.1) {
				Drive.driveCartesian(0, .4, 0);
			}else if (!finished && angle > -85) {
				Drive.driveCartesian(0, 0, -.25);
			}else {
				finished = true;
				Drive.drive(0, 0, 0);
			}
		}
	}
	
	public static void step(){
		double now = time.get();
		if (now < 4.5){
			
			//Arm.GoAngle(1.528); 
			Arm.GoIR(1.4); //guess
			
			Arm.SetCollector(0);
			Drive.driveCartesian(0, 0, 0);
		} else {
			if (now < 4.5) {
				Arm.SetArm(0);
				Arm.SetCollector(0); //wait
				Drive.drive(0, 0, 0);
			} else if(now < 6.7){  //for 1.9s
				Arm.SetArm(0);
				Arm.SetCollector(-0.5); //bring tape out
				Drive.drive(0, 0, 0);
			} else if (now < 7){ //for .5s
				Arm.SetArm(-0.8);
				Arm.SetCollector(0); //drop arm
				Drive.drive(0, 0, 0);
			} else if(now < 7.8){ //for .5s
				Arm.SetArm(0);
				Arm.SetCollector(0); //w
				Drive.drive(0, 0, 0);
			} else if (now < 8.5){ //for .5s
				Arm.SetArm(0);
				Arm.SetCollector(0.3); //hook container with tape
				Drive.drive(0, 0, 0);				
			} else if (now < 11.2) { //for 2.7s
				Arm.SetArm(1);
				Arm.SetCollector(0.3); //pull back and lift arm
				Drive.drive(.25, 180, 0);
			} else if (now < 12.7) { //for 1.5s
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
