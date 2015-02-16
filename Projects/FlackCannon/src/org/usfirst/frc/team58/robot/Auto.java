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
				crab();
				break;
			case 3:
				container();
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
	}

	private static void forward(){
		if (time.get()<2){
			Drive.drive(0.5, 0, 0);
		}
	}
	private static double start=0;
	private static void crab() {
		double now = time.get();
	if (Arm.GetAngle() < 1.67){
		Arm.SetArm(.5);
	}else {
		if (start <= 0.1){
			start = now;
		}
		if (start + 5 > now){
			Drive.driveCartesian(.5, 0, 0);
		}
	}
	}
	
	public static void container() {
		double now = time.get();
		if (now < 3){
			Arm.SetArm(0.5);
		}else {
			Arm.SetArm(0);
			double angle = Drive.getGyro();
			if (now < 4.5){
				Drive.driveCartesian(0, .4, 0);
			}else if (angle > -85) {
				Drive.driveCartesian(0, 0, -.25);
			}else {
				Drive.driveCartesian(0, 0, 0);
			}
		}
	}
}
