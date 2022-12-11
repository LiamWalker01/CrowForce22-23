package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "KiranAuto", group = "Linear OpMode" )

public class KiranAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor middleslideDrive = null;
    private Servo rightgripperDrive = null;
    private Servo leftgripperDrive = null;




    @Override
    public void runOpMode() throws InterruptedException {
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
        rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        ElapsedTime mRuntime = new ElapsedTime();
        //ColorSensor colorSensor = hardwareMap.colorSensor.get("color_sensor");
        DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightgripperDrive.setDirection(Servo.Direction.REVERSE);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);
        double gripperStartPosition = 0.1;
        double gripperEndPosition = 0;
        waitForStart();
        while (opModeIsActive()) {

            moveStrafe(-2,1,1000 );
            if (frontrightDrive.getCurrentPosition() < 4500) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);
            }
            //moves straight

            if (frontrightDrive.getCurrentPosition() < 5250) {
                frontleftDrive.setPower(1);
                backrightDrive.setPower(-1);
                backleftDrive.setPower(-1);
                frontrightDrive.setPower(1);

            }
            //moves up to be in postion to lower cone
            if(middleslideDrive.getCurrentPosition() <= 4000) {
                middleslideDrive.setPower(0.9);
            }
            if (frontrightDrive.getCurrentPosition() < 5300) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);

            }


            rightgripperDrive.setPosition(gripperStartPosition);
            leftgripperDrive.setPosition(gripperStartPosition);
            if (frontrightDrive.getCurrentPosition() < 5400) {
                frontleftDrive.setPower(1);
                backrightDrive.setPower(-1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);

            }
            if(middleslideDrive.getCurrentPosition() <= 500) {
                middleslideDrive.setPower(0.9);
            }
            //back away from junction
            if (frontrightDrive.getCurrentPosition() < 6000) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);

            }
            //goes left
            if (frontrightDrive.getCurrentPosition() < 6300) {
                frontleftDrive.setPower(1);

                backleftDrive.setPower(1);


            }

            //turns down
            if (frontrightDrive.getCurrentPosition() < 6800) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);
            }
            //moves down and picks up cone
            if (frontrightDrive.getCurrentPosition() < 7500) {
                frontleftDrive.setPower(1);
                backrightDrive.setPower(-1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);

            }
            //moves backwards
            if (frontrightDrive.getCurrentPosition() < 8000) {

                backrightDrive.setPower(1);

                frontrightDrive.setPower(1);

            }
            if (frontrightDrive.getCurrentPosition() < 8250) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);

            }
            //moves left
            if (frontrightDrive.getCurrentPosition() < 9000) {
                frontleftDrive.setPower(-1);
                backrightDrive.setPower(1);
                backleftDrive.setPower(1);
                frontrightDrive.setPower(-1);
            }
        }
    }
private void moveStrafe(double direction, double speed, double distance) {

        boolean left = false;
        if (direction < 0) {left = true;}

        if (left = true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
            }

        }

        if(left = false) {
            while (frontleftDrive.getCurrentPosition() < distance) {
                frontleftDrive.setPower(speed);
                backrightDrive.setPower(-speed);
                backleftDrive.setPower(-speed);
                frontrightDrive.setPower(speed);
            }
        }



}
}
