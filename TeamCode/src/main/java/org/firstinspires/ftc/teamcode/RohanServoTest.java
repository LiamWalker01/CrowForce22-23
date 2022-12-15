package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="RohanServoTest", group="Test Teleops")
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


        Boolean rBPress = false;
        Boolean lBPress = false;

        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;

                if (lBPress  == true) {
                    rightgripperDrive.setPosition(0);
                    rightgripperDrive.setPosition(0);
                }

                if (rBPress == true) {
                    rightgripperDrive.setPosition(1);
                    leftgripperDrive.setPosition(1);
                }
        }
    }
}

