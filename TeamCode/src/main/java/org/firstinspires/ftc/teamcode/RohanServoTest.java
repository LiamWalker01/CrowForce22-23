package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="RohanServoTest", group="Test Teleops")
//@Disabled
public class RohanServoTest extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();


    //@Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Servo rightGripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        Servo leftGripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        boolean rBPress;
        boolean lBPress;

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;

                if (lBPress) {
                    rightGripperDrive.setPosition(0);
                    leftGripperDrive.setPosition(0);
                }

                if (rBPress) {
                    rightGripperDrive.setPosition(1);
                    leftGripperDrive.setPosition(1);
                }

            telemetry.addData("Right Servo Position", rightGripperDrive.getPosition());
            telemetry.addData("Left Servo Position", leftGripperDrive.getPosition());
            telemetry.addData("Left Button Press", lBPress);
            telemetry.addData("Right Button Press", rBPress);
            telemetry.update();
        }
    }
}

