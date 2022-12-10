package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a Mechnum Teleop for a four wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *

 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

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


    //@Override
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
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
        rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        //DistanceSensor frontDistance = hardwareMap.get(DistanceSensor.class, "front_distance");
        Boolean dpad_up = false;
        Boolean dpad_down = false;
        //Boolean dpad_right = false;
        //Boolean dpad_left = false;
        Boolean aPress = false;
        Boolean bPress = false;
        Boolean xPress = false;
        Boolean yPress = false;
        Boolean rBPress = false;
        Boolean lBPress = false;
        double sensitivity = 0;
        Boolean clampClose = false;
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightgripperDrive.setDirection(Servo.Direction.REVERSE);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            aPress = gamepad1.a;
            bPress = gamepad1.b;
            dpad_up = gamepad2.dpad_up;
            dpad_down = gamepad2.dpad_down;


            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;
            double gripperStartPosition = 0.1;
            double gripperEndPosition = 0;
            rightgripperDrive.setPosition(gripperStartPosition);
            leftgripperDrive.setPosition(gripperStartPosition);
            middleslideDrive.setPower(0);
            //middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //setting the linear slides to go through sets. so like motor encoders

            while (true) {
                //rightgripperDrive.setPosition(gamepad2.right_stick_x);
                //leftgripperDrive.setPosition(gamepad2.left_stick_x);
                middleslideDrive.setPower(0);
                if (dpad_up == true && middleslideDrive.getCurrentPosition() <= 4000) {
                    if (middleslideDrive.getCurrentPosition() <= 4000) {
                        middleslideDrive.setPower(1);
                    }

                }
                if (dpad_down == true && middleslideDrive.getCurrentPosition() >= 70) {

                    if (middleslideDrive.getCurrentPosition() >= 70) {
                        middleslideDrive.setPower(-1);
                    }

                }
                if (dpad_down == false && dpad_up == false) {
                    middleslideDrive.setPower(0);
                }


                if (rBPress == true) {
                    rightgripperDrive.setPosition(gripperEndPosition);
                    leftgripperDrive.setPosition(gripperEndPosition);

                }

                if (lBPress == true) {
                    rightgripperDrive.setPosition(gripperStartPosition);
                    leftgripperDrive.setPosition(gripperStartPosition);
                }

                double vertical;
                double horizontal;
                double pivot;


                //moves robot by using joystick position
                aPress = gamepad1.a;
                bPress = gamepad1.b;

                if (aPress) {
                    sensitivity = 0.5;
                }
                if (bPress) {
                    sensitivity = 1;
                }
                vertical = (-gamepad1.left_stick_y);
                horizontal = (gamepad1.left_stick_x);
                pivot = (gamepad1.right_stick_x);

                frontrightDrive.setPower(sensitivity * (pivot + (-vertical + horizontal)));
                frontleftDrive.setPower(sensitivity * (-pivot + (-vertical - horizontal)));
                backleftDrive.setPower(sensitivity * (-pivot + (-vertical + horizontal)));
                backrightDrive.setPower(sensitivity * (pivot + (-vertical - horizontal)));

                telemetry.addData("Sensitivity", "is: " + sensitivity);
                telemetry.addData("Motor 1 Power", "is: " + sensitivity * (pivot + (-vertical + horizontal)));
                telemetry.addData("Motor 2 Power", "is: " + sensitivity * (-pivot + (-vertical - horizontal)));
                telemetry.addData("Motor 3 Power", "is: " + sensitivity * (-pivot + (-vertical + horizontal)));
                telemetry.addData("Motor 4 Power", "is: " + sensitivity * (pivot + (-vertical - horizontal)));

                dpad_up = gamepad2.dpad_up;
                dpad_down = gamepad2.dpad_down;


                rBPress = gamepad2.right_bumper;
                lBPress = gamepad2.left_bumper;

                // Show the elapsed game time and wheel power.
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                //telemetry.addData("Motors", "left (%.2f), right (%.2f)", pivot + (vertical+horizontal), pivot+ (-vertical-horizontal));
                telemetry.addData("Right Bumber", "T/F:" + rBPress);
                telemetry.addData("SlidePosition", "Encoders:" + middleslideDrive.getCurrentPosition());
                telemetry.addData("Right Grips", "Position:" + rightgripperDrive.getPosition());
                telemetry.addData("Left Grips", "Position:" + leftgripperDrive.getPosition());
                telemetry.addData("Driver 2 Stick:", "Driver 2 Left: " + gamepad2.right_stick_x);
                telemetry.addData("Driver 2 Stick:", "Driver 2 Left: " + gamepad2.left_stick_x);
                telemetry.update();
            }
        }
    }
}
