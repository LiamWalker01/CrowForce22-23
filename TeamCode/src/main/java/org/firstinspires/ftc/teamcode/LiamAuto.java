package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "LiamAuto", group = "Linear OpMode" )



public class LiamAuto extends LinearOpMode {
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
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
        frontleftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backleftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backrightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        middleslideDrive.setDirection((DcMotorSimple.Direction.REVERSE));
        middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        middleslideDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        double tile = 1000;

        ElapsedTime matchTime = new ElapsedTime();
        telemetry.addData("Run Time:", matchTime);
        telemetry.update();

        moveSimpleByEncoder(.5, 1000, 1);
        moveSimpleSlidesByLevel(1, 3);

    }

    public void moveSimpleByEncoder(double power, double distance, int direction) {
        if (direction == 1 && frontleftDrive.getCurrentPosition() < distance) {
            frontleftDrive.setPower(power);
            frontrightDrive.setPower(power);
            backleftDrive.setPower(power);
            backrightDrive.setPower(power);
        }
        if (direction == 2 && frontleftDrive.getCurrentPosition() < distance) {
            frontleftDrive.setPower(power);
            frontrightDrive.setPower(-power);
            backleftDrive.setPower(-power);
            backrightDrive.setPower(power);
        }
        if (direction == 3 && frontleftDrive.getCurrentPosition() < distance) {
            frontleftDrive.setPower(-power);
            frontrightDrive.setPower(-power);
            backleftDrive.setPower(-power);
            backrightDrive.setPower(-power);
        }
        if (direction == 4 & frontleftDrive.getCurrentPosition() < distance) {
            frontleftDrive.setPower(-power);
            frontrightDrive.setPower(power);
            backleftDrive.setPower(power);
            backrightDrive.setPower(-power);
        }

    }

    public void moveSimpleSlidesByLevel(double power, double level) {

        double position = 0;
        if (level == 1) {
            position = 70;
        }
        if (level == 2) {
            position = 2000;
        }
        if (level == 3) {
            position = 4000;
        }
        if (middleslideDrive.getCurrentPosition() < position) {
            while (middleslideDrive.getCurrentPosition() < position) {
                middleslideDrive.setPower(power);
                telemetry.update();
            }
        }
        if (middleslideDrive.getCurrentPosition() > position) {
            while (middleslideDrive.getCurrentPosition() > position) {
                middleslideDrive.setPower(-power);
                telemetry.update();
            }
        }
    }
}