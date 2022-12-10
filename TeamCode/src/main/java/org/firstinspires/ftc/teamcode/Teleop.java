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
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
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
        Boolean clampClose;
        Boolean dpad_up1;
        Boolean dpad_up2;
        Boolean dpad_down1;
        Boolean dpad_down2;
        Boolean rBPress;
        Boolean lBPress;
        boolean hasPressedUp = false;
        boolean hasPressedDown = false;
        boolean automaticSlides = true;


        // Define Movement Variables
        double power = .5;
        double slidesPosition = 0;
        double vertical;
        double horizontal;
        double pivot;
        double gripperStartPosition = 0.1;
        double gripperEndPosition = 0;

        // Define Motors
        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightgripperDrive.setDirection(Servo.Direction.REVERSE);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Assigns dpad and left right buttons
            rightgripperDrive.setPosition(gripperStartPosition);
            leftgripperDrive.setPosition(gripperStartPosition);
            middleslideDrive.setPower(0);
            //middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //setting the linear slides to go through sets. so like motor encoders

            //rightgripperDrive.setPosition(gamepad2.right_stick_x);
            //leftgripperDrive.setPosition(gamepad2.left_stick_x);

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

            if(aPress2) {
                automaticSlides = true;
            }
            if (yPress2) {
                automaticSlides = false;
            }
            if (xPress2) {
                slidesPosition = 0;
            }

            if (automaticSlides == true) {
                // Sets predetermined location for slides
                if (dpad_up2 && hasPressedUp == false) {
                    slidesPosition += 1;
                    hasPressedUp = true;
                } else if (dpad_down2 && hasPressedDown == false) {
                    slidesPosition -= 1;
                    hasPressedDown = true;
                } else if (dpad_up2 == false && dpad_down2 == false) {
                    hasPressedUp = false;
                    hasPressedDown = false;
                } else if (slidesPosition > 3) {
                    slidesPosition = 3;
                } else if (slidesPosition < 0) {
                    slidesPosition = 0;
                }

                // Moves Motor to set position

                if ((slidesPosition == 1 && middleslideDrive.getCurrentPosition() != 1333)) {
                    if (middleslideDrive.getCurrentPosition() < 1333) {
                        middleslideDrive.setPower(-1);
                    } else if (middleslideDrive.getCurrentPosition() > 1333) {
                        middleslideDrive.setPower(1);
                    }
                }
                if ((slidesPosition == 1 && middleslideDrive.getCurrentPosition() != 2666)) {
                    if (middleslideDrive.getCurrentPosition() < 2666) {
                        middleslideDrive.setPower(-1);
                    } else if (middleslideDrive.getCurrentPosition() > 2666) {
                        middleslideDrive.setPower(1);
                    }
                }
                if ((slidesPosition == 1 && middleslideDrive.getCurrentPosition() != 4000)) {
                    if (middleslideDrive.getCurrentPosition() < 4000) {
                        middleslideDrive.setPower(-1);
                    } else if (middleslideDrive.getCurrentPosition() > 4000) {
                        middleslideDrive.setPower(1);
                    }
                }

            } else if (automaticSlides == false) {
                middleslideDrive.setPower(0);
                if (dpad_up2 == true && middleslideDrive.getCurrentPosition() <= 4000) {

                    if (middleslideDrive.getCurrentPosition() <= 4000) {
                        middleslideDrive.setPower(1);
                    }
                }
                if (dpad_down2 == true && middleslideDrive.getCurrentPosition() >= 70) {

                    if (middleslideDrive.getCurrentPosition() >= 70) {
                        middleslideDrive.setPower(-1);
                    }
                }
                if (dpad_down2 == false && dpad_up2 == false) {
                    middleslideDrive.setPower(0);
                }
            }


            if (rBPress == true) {
                rightgripperDrive.setPosition(gripperEndPosition);
                leftgripperDrive.setPosition(gripperEndPosition);
            }

            if (lBPress == true) {
                rightgripperDrive.setPosition(gripperStartPosition);
                leftgripperDrive.setPosition(gripperStartPosition);
            }

            // Moves robot by using joystick position
            // A and B button changes sensitivity



            if (yPress1) {
                power = 1;
            } else if (aPress1) {
                power = .5;
            }

            vertical = (-gamepad1.left_stick_y);
            horizontal = (gamepad1.left_stick_x);
            pivot = (gamepad1.right_stick_x);

            frontrightDrive.setPower(power * (pivot + (-vertical + horizontal)));
            frontleftDrive.setPower(power * (-pivot + (-vertical - horizontal)));
            backleftDrive.setPower(power * (-pivot + (-vertical + horizontal)));
            backrightDrive.setPower(power * (pivot + (-vertical - horizontal)));

            // Show the run time, slide position, right & left grips, left & right driver stick, power
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("SlidePosition", "Encoders:" + middleslideDrive.getCurrentPosition());
            telemetry.addData("slidesPositionState", "Variable: " + slidesPosition);
            telemetry.addData("Right Grips", "Position:" + rightgripperDrive.getPosition());
            telemetry.addData("Left Grips", "Position:" + leftgripperDrive.getPosition());
            telemetry.addData("Power is:", power);
            telemetry.update();
            //telemetry.addData("Driver 2 Stick:", "Right: " + gamepad2.right_stick_x);
            //telemetry.addData("Driver 2 Stick:", "Left: " + gamepad2.left_stick_x);
            //telemetry.addData("Motor 1 Power is:", power * (pivot + (-vertical + horizontal)));
            //telemetry.addData("Motor 2 Power is:",  power * (-pivot + (-vertical - horizontal)));
            //telemetry.addData("Motor 3 Power is:", power * (-pivot + (-vertical + horizontal)));
            //telemetry.addData("Motor 4 Power is:", power * (pivot + (-vertical - horizontal)));
            //telemetry.addData("Right Bumper", "T/F:" + rBPress);
            // telemetry.addData("Motors", "left (%.2f), right (%.2f)", pivot + (vertical+horizontal), pivot+ (-vertical-horizontal));

        }
    }
}
