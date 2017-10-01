package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryley on 9/25/17.
 */
@TeleOp(name="CF_EncoderTest", group = "test")
//@Disabled
public class CF_EncoderTest extends OpMode {
    CF_Hardware robot = new CF_Hardware();

    // Mechanum Wheel Powers
    double LFPower = 0.0;
    double RFPower = 0.0;
    double LRPower = 0.0;
    double RRPower = 0.0;

    // Variables for the drive strafe and rotate input commands that are
    // Mapped to the gamepad's axis
    double drive;
    double strafe;
    double rotate;

    // Claw Position
    double clawPos = 0;

    // Variable mapped to the gamepad's axis for the lift
    double leftLift = 0;

    // Mode number, should be changed to an enum in the future
    int mode = 0;


    public void init() {
        robot.init(hardwareMap);
        telemetry.addData("", "init");
    }

    public void loop(){
        // Updates the functions that control the robot
        updateMode();
        drive();
        runLeftClaw();
        runLift();

        // Add mode telemetry
        telemetry.clearAll();
        telemetry.addData("Mode", mode);
        telemetry.addData("Encoder Counts LF", robot.leftFront.getCurrentPosition());
        telemetry.addData("Encoder Count RF", robot.rightFront.getCurrentPosition());
        telemetry.update();

    }

    public void runMechWheels() {
        // Assigns the gamepad inputs to the variables to hold them
        drive = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        // Strafe + Drive + Rotate
        LFPower = -strafe + drive + rotate;
        RFPower = +strafe + drive - rotate;
        LRPower = +strafe + drive + rotate;
        RRPower = -strafe + drive - rotate;
        setMechPowers(LFPower, RFPower, LRPower, RRPower);
    }

    public void runMechWheelsSlow() {
        // This is the same as runMechWheels() but half power
        // Assigns the gamepad inputs to the variables to hold them
        drive = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        // Strafe + Drive + Rotate
        LFPower = -strafe + drive + rotate;
        RFPower = +strafe + drive - rotate;
        LRPower = +strafe + drive + rotate;
        RRPower = -strafe + drive - rotate;
        setMechPowers(0.5 * LFPower, 0.5 * RFPower, 0.5 * LRPower, 0.5 * RRPower);
    }

    public void drive() {
        // Checks modes
        if(mode == 0){
            // Normal mech wheel mode
            runMechWheels();
        }

        if(mode == 1) {
            // Skidsteer mode
            robot.leftFront.setPower(gamepad1.right_stick_y);
            robot.leftRear.setPower(gamepad1.right_stick_y);
            robot.rightFront.setPower(gamepad1.left_stick_y);
            robot.rightRear.setPower(gamepad1.left_stick_y);
        }

        if(mode == 2) {
            // Slow mech wheel mode
            runMechWheelsSlow();
        }
    }

    public void updateMode() {
        // Updates the mode when gamepad1 'a' button is pressed, rools over at mode = 2
        if(gamepad1.a) {
            if(mode == 2) {
                // Roll over mode
                mode = 0;
            }
            else if(mode < 2) {
                // Increments the mode
                mode++;
            }

            // Debounced
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {}
        }
    }

    public void runLift() {
        // Assings the gamepad input to the appropriate variable
        leftLift = gamepad2.left_stick_y;

        // Sets the power for the lift motor to the gamepad input/variable
        robot.leftLiftMotor.setPower(leftLift);


    }

    public void runLeftClaw() {
        // As long as the claw is < 1 and > 0, the claw can move
        if(gamepad2.y) {
            // Y makes the claw set higher
            if(clawPos < 1) {
                clawPos += 0.01;
            }
        }

        if(gamepad2.a) {
            // A makes the claw set lower
            if(clawPos > 0) {
                clawPos -= 0.01;
            }
        }

        // Acutally sets the position to the variable
        robot.leftClaw.setPosition(clawPos);
    }

    public void setMechPowers(double LF, double RF, double LR, double RR) {
        // Sets the motor powers convinently to the correct motors.  Left Front, Right Front, Left Rear, Right Rear
        robot.rightRear.setPower(RR);
        robot.rightFront.setPower(RF);
        robot.leftRear.setPower(LR);
        robot.leftFront.setPower(LF);
    }
}
