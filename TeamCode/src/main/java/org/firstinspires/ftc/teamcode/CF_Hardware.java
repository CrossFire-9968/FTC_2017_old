package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
/**
 * Created by Ryley on 9/15/17.
 */


public class CF_Hardware {
    public DcMotor rightFront = null;
    public DcMotor rightRear = null;
    public DcMotor leftFront = null;
    public DcMotor leftRear = null;
    public DcMotor leftLiftMotor = null;

    public Servo leftClaw = null;

    HardwareMap hwMap = null;

    public CF_Hardware() {}

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        rightFront = hwMap.get(DcMotor.class, "motorOne");
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);

        rightRear = hwMap.get(DcMotor.class, "motorTwo");
        rightRear.setDirection(DcMotorSimple.Direction.FORWARD);

        leftRear = hwMap.get(DcMotor.class, "motorThree");
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront = hwMap.get(DcMotor.class, "motorFour");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftLiftMotor = hwMap.get(DcMotor.class, "leftLiftMotor");
        leftLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftClaw = hwMap.get(Servo.class, "leftClaw");
    }
}
