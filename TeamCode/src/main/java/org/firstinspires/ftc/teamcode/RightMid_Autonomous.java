package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightMax_Autonomous", group = "Drew's Auto" )



public class RightMid_Autonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor middleslideDrive = null;
    private Servo rightgripperDrive = null;
    private Servo leftgripperDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
        rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        rightgripperDrive.setDirection(Servo.Direction.FORWARD);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);
        middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleslideDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        rightgripperDrive.setPosition(0);
        leftgripperDrive.setPosition(1);
        leftgripperDrive.setPosition(0.1);
        setSlider(3);

        double tile = 32;

        moveEncoders(0, 30, 0.1*tile);
        moveEncoders(-90, 50, tile);
        moveEncoders(0, 50, tile);
        turnTime(-0.5, 0.5);
        moveEncoders(0, 20, 0.1*tile);
        setSlider(2);
        leftgripperDrive.setPosition(1);
        setSlider(3);
        moveEncoders(0, 20, -0.2*tile);
        setSlider(1);

        /* moveGyro( 0, 30, 2);

        moveTime(0, 60, 3);


         */
    }

    public void moveGyro(double angle, double speed, double distance) {
    }

    public void turnGyro(double direction, double speed) {
    }

    public void moveEncoders(double angle, double speed, double distance) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double sin = Math.sin(angle - Math.PI / 4);
        double cos = Math.cos(angle - Math.PI / 4);

        while (frontrightDrive.getCurrentPosition() <= distance * sin) {
            frontleftDrive.setPower(speed * cos);
            frontrightDrive.setPower(speed * sin);
            backleftDrive.setPower(speed * sin);
            backleftDrive.setPower(speed * cos);
        }
        if (frontrightDrive.getCurrentPosition() > distance * sin) {
            frontleftDrive.setPower(0);
            frontrightDrive.setPower(0);
            backleftDrive.setPower(0);
            backleftDrive.setPower(0);
        }

    }

    public void turnEncoders(double angle, double speed) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (angle > 0) {
            while (frontrightDrive.getCurrentPosition() <= (angle/360)*348.5) {
                frontleftDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
                backrightDrive.setPower(-speed);

            }

        }
        if (angle < 0) {
            while (frontrightDrive.getCurrentPosition() >= (angle/360)*348.5) {
                frontleftDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
                backrightDrive.setPower(-speed);

            }
        }
    }

    public void moveTime(double angle, double speed, double time) {
        double sin = Math.sin(angle - Math.PI / 4);
        double cos = Math.cos(angle - Math.PI / 4);
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() <= time) {
            if (movementTime.time() < time) {
                frontleftDrive.setPower(speed * cos);
                frontrightDrive.setPower(speed * sin);
                backleftDrive.setPower(speed * sin);
                backleftDrive.setPower(speed * cos);
            } else {
                frontleftDrive.setPower(0);
                frontrightDrive.setPower(0);
                backleftDrive.setPower(0);
                backleftDrive.setPower(0);
            }

        }
    }

    public void turnTime(double direction, double time) {
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() < time) {
            frontleftDrive.setPower(-direction);
            frontrightDrive.setPower(direction);
            backleftDrive.setPower(-direction);
            backrightDrive.setPower(direction);
        }
    }
    public void setSlider(double level) {
        //middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //middleslideDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double position = 0;
        if (level == 1) {position = 70;}
        if (level == 2) {position = 2000;}
        if (level == 3) {position = 4000;}
        if (middleslideDrive.getCurrentPosition() < position) {
            while (middleslideDrive.getCurrentPosition()< position) {middleslideDrive.setPower(0.3);}

        }
        if (middleslideDrive.getCurrentPosition() > position) {
            while (middleslideDrive.getCurrentPosition()> position) {middleslideDrive.setPower(-0.3);}
            }

    }
}