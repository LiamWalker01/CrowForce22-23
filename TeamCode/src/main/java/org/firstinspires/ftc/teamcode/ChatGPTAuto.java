package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "LeftMin_Autonomous", group = "Drew's Auto" )

public class ChatGPTAuto extends LinearOpMode {
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

        // Initialize motors and set their directions
        frontleftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backleftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backrightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        // Move the robot forward for 1000 ticks
        frontleftDrive.setTargetPosition(1000);
        frontrightDrive.setTargetPosition(1000);
        backleftDrive.setTargetPosition(1000);
        backrightDrive.setTargetPosition(1000);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleftDrive.setPower(0.5);
        frontrightDrive.setPower(0.5);
        backleftDrive.setPower(0.5);
        backrightDrive.setPower(0.5);

        while (frontleftDrive.isBusy() && frontrightDrive.isBusy() && backleftDrive.isBusy() && backrightDrive.isBusy()) {
            // Wait for the motors to reach their target positions
        }

        frontleftDrive.setPower(0);
        frontrightDrive.setPower(0);
        backleftDrive.setPower(0);
        backrightDrive.setPower(0);

        // Turn the robot left for 2000 ticks
        frontleftDrive.setTargetPosition(-2000);
        frontrightDrive.setTargetPosition(2000);
        backleftDrive.setTargetPosition(-2000);
        backrightDrive.setTargetPosition(2000);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleftDrive.setPower(0.5);
        frontrightDrive.setPower(0.5);
        backleftDrive.setPower(0.5);
        backrightDrive.setPower(0.5);

        while (frontleftDrive.isBusy() && frontrightDrive.isBusy() && backleftDrive.isBusy() && backrightDrive.isBusy()) {
            // Wait for the motors to reach their target positions
        }

        frontleftDrive.setPower(0);
        frontrightDrive.setPower(0);
        backleftDrive.setPower(0);
        backrightDrive.setPower(0);
    }
}



