package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name="Teleop", group="Linear Opmode")
//@Disabled
public class Teleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor middleslideDrive = null;
    private Servo rightgripperDrive = null;
    private Servo leftgripperDrive = null;


    // @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive= hardwareMap.get(DcMotor.class, "middle_slides_drive");
        rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        // DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");

        // Define Boolean Buttons
        Boolean dpad_right;
        Boolean dpad_left;
        Boolean aPress1;
        Boolean bPress1;
        Boolean xPress1;
        Boolean yPress1;
        Boolean aPress2;
        Boolean bPress2;
        Boolean xPress2;
        Boolean yPress2;
        Boolean dpad_up1 = false;
        Boolean dpad_up2 = false;
        Boolean dpad_down1 = false;
        Boolean dpad_down2 = false;
        Boolean rBPress;
        Boolean lBPress;
        boolean hasPressedUp = false;
        boolean hasPressedDown = false;
        boolean automaticSlides = false;


        // Define Movement Variables
        double power = .5;
        double slidesPosition = 0;
        double vertical;
        double horizontal;
        double pivot;

        // Define Motors & starts intial values
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

        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Stuff we want run once after start
        double slidesInitialPosition = middleslideDrive.getCurrentPosition();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Assign Variables to buttons
            dpad_up2 = gamepad2.dpad_up;
            dpad_up1 = gamepad1.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            dpad_down1 = gamepad1.dpad_down;

            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;

            aPress1 = gamepad1.a;
            bPress1 = gamepad1.b;
            yPress1 = gamepad1.y;
            xPress1 = gamepad1.x;
            aPress2 = gamepad2.a;
            bPress2 = gamepad2.b;
            yPress2 = gamepad2.y;
            xPress2 = gamepad2.x;

            //slides code
            if (aPress2) {
                automaticSlides = true;
            }
            if (yPress2) {
                automaticSlides = false;
            }
            if (xPress2) {
                slidesPosition = 0;
            }

            if (dpad_up2 && middleslideDrive.getCurrentPosition() <= slidesInitialPosition + 4000) {

                if (middleslideDrive.getCurrentPosition() <= slidesInitialPosition + 4000) {
                    middleslideDrive.setPower(-1);
                }
            }

            if (dpad_down2 & middleslideDrive.getCurrentPosition() >= slidesInitialPosition) {
                if (middleslideDrive.getCurrentPosition() >= slidesInitialPosition) {
                    middleslideDrive.setPower(1);
                }
            }

            if (!dpad_down2 && !dpad_up2) {
                middleslideDrive.setPower(0);
            }
            // Controls grippers
            if (lBPress) {
                leftgripperDrive.setPosition(.77);
                rightgripperDrive.setPosition(.12);
            }
            if (rBPress) {
                leftgripperDrive.setPosition(.505);
                rightgripperDrive.setPosition(.35);
            }

            // Moves robot by using joystick position
            // A and B button changes power
            if (yPress1) {
                power = 1;
            } else if (aPress1) {
                power = .5;
            }

            vertical = -gamepad1.left_stick_y;
            horizontal = gamepad1.left_stick_x;
            pivot = gamepad1.right_stick_x;

            frontrightDrive.setPower(power * (pivot + (-vertical + horizontal)));
            frontleftDrive.setPower(power * (-pivot + (-vertical - horizontal)));
            backleftDrive.setPower(power * (-pivot + (-vertical + horizontal)));
            backrightDrive.setPower(power * (pivot + (-vertical - horizontal)));

            // Telemetry
            telemetry.addData("Run Time", runtime.toString());
            telemetry.addData("Front Right Encoder", frontrightDrive.getCurrentPosition());
            telemetry.addData("Front Left Encoder", frontleftDrive.getCurrentPosition());
            telemetry.addData("Back Right Encoder", backrightDrive.getCurrentPosition());
            telemetry.addData("Back Left Encoder", backleftDrive.getCurrentPosition());
            telemetry.addData("Middle Slides Encoder", middleslideDrive.getCurrentPosition());

            telemetry.addData("Motor 1 Power is:", power * (pivot + (-vertical + horizontal)));
            telemetry.addData("Motor 2 Power is:",  power * (-pivot + (-vertical - horizontal)));
            telemetry.addData("Motor 3 Power is:", power * (-pivot + (-vertical + horizontal)));
            telemetry.addData("Motor 4 Power is:", power * (pivot + (-vertical - horizontal)));

            telemetry.update();

        }
    }
}