package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by dawso on 10/1/2017.
 */


@Autonomous(name = "Simple Auto", group = "Sensor")
//@Disabled                            // Comment this out to add to the opmode list
public class CF_Simple_Auto extends LinearOpMode
{
   CF_Hardware robot = new CF_Hardware();
   CF_Color_Sensor sensor = new CF_Color_Sensor();

   private enum states
   {
      BACKUP, JEWELPUSHER
   }

   @Override
   public void runOpMode() throws InterruptedException
   {
      sensor.init();

//      states State = states.BACKUP;
//
      float hsvValues[] = {0F, 0F, 0F};
      final float values[] = hsvValues;

      waitForStart();
//
      while (opModeIsActive())
      {
//         switch (State)
//         {
//            case BACKUP:
//
//               //Set direction, distance, and motor powers of mecanum wheels
//               robot.setMecanumEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//               //Tell encoders to run to a set position
//               robot.setMecanumEncoderMode(DcMotor.RunMode.RUN_TO_POSITION);
//               robot.setMecanumPowers(0.5, 0.5, 0.5, 0.5);
//               robot.setMecanumEncoderTargetPosition(-850, -850, -850, -850);
//               telemetry.addData("1", " and done");
//
//               telemetry.update();
//               break;
////            case JEWELPUSHER:
//         if (sensor.hueVal = true)
//         {
//            //robot.SetJewelPusherPositoin()
//         }
//
//         else if (sensor.hueVal = false)
//         {
//            //robot.SetJewelPusherPosition(0.70);
//         }

      }

   }
}