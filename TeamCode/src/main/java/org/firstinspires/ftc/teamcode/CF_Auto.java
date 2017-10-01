package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryley on 9/29/17.
 */

// -850 encoder counts to 1 ft approximately

@Autonomous(name = "CF_Auto", group = "test")
//@Disabled
public class CF_Auto extends LinearOpMode {
    CF_Hardware robot = new CF_Hardware();

    // Mechanum Wheel Powers
    double LFPower = 0.0;
    double RFPower = 0.0;
    double LRPower = 0.0;
    double RRPower = 0.0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        encoderDrive(850, 0.5);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {}

        encoderStrafe(2000, 0.5);
    }

    public void encoderStrafe(double counts, double power) {
        LFPower = -power;
        RFPower = power;
        LRPower = power;
        RRPower = -power;
        int desired = Math.abs(robot.leftFront.getCurrentPosition()) + (int)counts;
        while(Math.abs(robot.rightFront.getCurrentPosition()) < desired) {
            setMechPowers(LFPower, RFPower, LRPower, RRPower);
        }
        setMechPowers(0,0,0,0);
    }

    public void encoderDrive(double counts, double power) {
        LFPower = -power;
        RFPower = -power;
        LRPower = -power;
        RRPower = -power;
        int desired = Math.abs(robot.leftFront.getCurrentPosition()) + (int)counts;
        while(Math.abs(robot.leftFront.getCurrentPosition()) < desired) {
            setMechPowers(LFPower, RFPower, LRPower, RRPower);
        }
        setMechPowers(0,0,0,0);
    }

    public void setMechPowers(double LF, double RF, double LR, double RR) {
        // Sets the motor powers conveniently to the correct motors.  Left Front, Right Front, Left Rear, Right Rear
        robot.rightRear.setPower(RR);
        robot.rightFront.setPower(RF);
        robot.leftRear.setPower(LR);
        robot.leftFront.setPower(LF);
    }
}
