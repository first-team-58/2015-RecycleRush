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
			case 6:
				stepPlatform();
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
		if (now < 3.5){ //run until 3.5 seconds is reached
			Arm.GoIR(1.3); //raise arm to tote heights
			Arm.SetCollector(0);
			Drive.driveCartesian(0, 0, 0);
		} else {
			if (now < 3.5) { //skip. time already reached
				Arm.SetArm(0);
				Arm.SetCollector(0); //does nothing
				Drive.drive(0, 0, 0);
			} else if(now < 5.45){ //run until 5.5 seconds is reached (for 1.58s)
				Arm.SetArm(0);
				Arm.SetCollector(-0.5); //extend tape 50% speed
				Drive.drive(0, 0, 0);
			} else if (now < 5.75){ //run until 5.8 seconds is reached (for .25s)
				Arm.SetArm(-1); //drop arm 80% speed
				Arm.SetCollector(0);
				Drive.drive(0, 0, 0);
			} else if(now < 6.55){ //run until 6.6 seconds is reached (for .75s)
				Arm.SetArm(0);
				Arm.SetCollector(0); //does nothing
				Drive.drive(0, 0, 0);
			} else if (now < 7.25){ //run until 7.3 seconds is reached (for .65s)
				Arm.SetArm(0);
				Arm.SetCollector(0.3); //pull tape back 30% speed
				Drive.drive(0, 0, 0);				
			} else if (now < 8.575) { //run until 10 seconds is reached (for 2.65s)
				Arm.SetArm(-1); //lift arm 100% speed
				Arm.SetCollector(0.7); //pull tape back 70% speed
				Drive.drive(.25, 180, 0); //drive back 25% speed w/ angle straight backwards
			} else if(now < 9.95){
				Arm.SetArm(-1); //lift arm 100% speed
				Arm.SetCollector(0); //pull tape back 70% speed
				Drive.drive(.125, 180, 0); //drive back 25% speed w/ angle straight backwards
			} else if (now < 11) { //run until 11.5 seconds is reached (for 1.5s)
				Arm.SetArm(-1); //
				Arm.SetCollector(0); //pull tape back 50% speed
				Drive.drive(.125, 180, 0); //angle straight backwards
			} else { //stop all functions
				Arm.SetCollector(0);
				Arm.SetArm(0);
				Drive.driveCartesian(0, 0, 0);
			}
		}
	}
	
	public static void stepPlatform(){
		double now = time.get();
		if (now < 3.5){ //run until 3.5 seconds is reached
			Arm.GoIR(1.2); //raise arm to tote heights
			Arm.SetCollector(0);
			Drive.driveCartesian(0, 0, 0);
		} else {
			if (now < 3.5) { //skip. time already reached
				Arm.SetArm(0);
				Arm.SetCollector(0); //does nothing
				Drive.drive(0, 0, 0);
			} else if(now < 5.45){ //run until 5.5 seconds is reached (for 1.58s)
				Arm.SetArm(0);
				Arm.SetCollector(-0.5); //extend tape 50% speed
				Drive.drive(0, 0, 0);
			} else if (now < 5.75){ //run until 5.8 seconds is reached (for .25s)
				Arm.SetArm(-1); //drop arm 80% speed
				Arm.SetCollector(0);
				Drive.drive(0, 0, 0);
			} else if(now < 6.55){ //run until 6.6 seconds is reached (for .75s)
				Arm.SetArm(0);
				Arm.SetCollector(0); //does nothing
				Drive.drive(0, 0, 0);
			} else if (now < 7.25){ //run until 7.3 seconds is reached (for .65s)
				Arm.SetArm(0);
				Arm.SetCollector(0.3); //pull tape back 30% speed
				Drive.drive(0, 0, 0);				
			} else if (now < 8.575) { //run until 10 seconds is reached (for 2.65s)
				Arm.SetArm(-1); //lift arm 100% speed
				Arm.SetCollector(0.7); //pull tape back 70% speed
				Drive.drive(.25, 180, 0); //drive back 25% speed w/ angle straight backwards
			} else if(now < 9.95){
				Arm.SetArm(-1); //lift arm 100% speed
				Arm.SetCollector(0); //pull tape back 70% speed
				Drive.drive(.25, 180, 0); //drive back 25% speed w/ angle straight backwards
			} else if (now < 11) { //run until 11.5 seconds is reached (for 1.5s)
				Arm.SetArm(-1); //
				Arm.SetCollector(0); //pull tape back 50% speed
				Drive.drive(0, 180, 0); //angle straight backwards
			} else { //stop all functions
				Arm.SetCollector(0);
				Arm.SetArm(0);
				Drive.driveCartesian(0, 0, 0);
			}
		}
	}
	
}