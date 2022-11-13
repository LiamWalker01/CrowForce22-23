package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Linear OpMode" )



public class Autonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
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

    public void turnEncoders(double direction, double speed) {
    }

    public void moveTime(double angle, double speed, double time) {
        double sin = Math.sin(angle - Math.PI / 4);
        double cos = Math.cos(angle - Math.PI / 4);
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() <= time + 0.3) {
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
}
