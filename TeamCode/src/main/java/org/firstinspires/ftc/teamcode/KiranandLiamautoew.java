package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "KiranandLiamAuto", group = "Teleops")

public class KiranandLiamautoew  extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor backleft = hardwareMap.dcMotor.get("back_left_drive");
        DcMotor backright = hardwareMap.dcMotor.get("back_right_drive");
        DcMotor frontleft = hardwareMap.dcMotor.get("front_left_drive");
        DcMotor frontright = hardwareMap.dcMotor.get("front_right_drive");
        ElapsedTime mRuntime = new ElapsedTime();
        //ColorSensor colorSensor = hardwareMap.colorSensor.get("color_sensor");
        //DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double speed = .5;

        waitForStart();


        while (opModeIsActive()) {

            telemetry.addData("Front Right Encoder", frontright.getCurrentPosition());
            telemetry.update();

            if (frontright.getCurrentPosition() < 4500) {
                frontleft.setPower(-speed);
                backright.setPower(speed);
                backleft.setPower(-speed);
                frontright.setPower(speed);
            }
            //moves straight

            if (frontright.getCurrentPosition() < 5250) {
                frontleft.setPower(-speed);
                backright.setPower(speed);
                backleft.setPower(speed);
                frontright.setPower(-speed);

            }
            //moves up to be in postion to lower cone
            //ARM
            //ARM DOWN
        /*while (frontright.getCurrentPosition() < 5300) {
            frontleft.setPower(-1);
            backright.setPower(1);
            backleft.setPower(1);
            frontright.setPower(-1);

        }
        //back away from junction
        while (frontright.getCurrentPosition() < 6000) {
            frontleft.setPower(1);
            backright.setPower(-1);
            backleft.setPower(1);
            frontright.setPower(-1);

        }
        //travels down
*/

        }


        }

    }



