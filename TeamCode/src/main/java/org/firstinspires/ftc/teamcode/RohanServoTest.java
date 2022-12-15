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
public class RohanServoTest extends LinearOpMode {

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
        Boolean clampClose = false;
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {


            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;
            rightgripperDrive.setPosition(0);
            leftgripperDrive.setPosition(0);
            middleslideDrive.setPower(0);
            //middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //setting the linear slides to go through sets. so like motor encoders



                if (rBPress == true) {
                    rightgripperDrive.setPosition(-60);
                    rightgripperDrive.setPosition(60);
                    //wait(2);

                }

                if (rBPress == true) {
                    rightgripperDrive.setPosition(0);
                    leftgripperDrive.setPosition(0);
                }
        }
    }
}

