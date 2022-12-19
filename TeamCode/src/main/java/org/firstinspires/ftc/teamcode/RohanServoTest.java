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
    private final ElapsedTime runtime = new ElapsedTime();


    //@Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        Boolean dpad_up2 = false;
        Boolean dpad_down2 = false;
        Boolean aPress2 = false;
        Boolean yPress2 = false;
        double gripperPosition = .75;
        double gripperPosition2 = .1;
        boolean hasPressedUp = false;
        boolean hasPressedDown = false;
        boolean hasPressedUp2 = false;
        boolean hasPressedDown2 = false;

        Servo rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        Servo leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");
        boolean rBPress;
        boolean lBPress;



        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            rBPress = gamepad2.right_bumper;
            lBPress = gamepad2.left_bumper;
            dpad_up2 = gamepad2.dpad_up;
            dpad_down2 = gamepad2.dpad_down;
            yPress2 = gamepad2.y;
            aPress2 = gamepad2.a;



            if (lBPress) {
                leftgripperDrive.setPosition(.77);
                rightgripperDrive.setPosition(.12);
            }
            if (rBPress) {
                leftgripperDrive.setPosition(.505);
                rightgripperDrive.setPosition(.35);
            }

            telemetry.addData("Right Servo Position", rightgripperDrive.getPosition());
            telemetry.addData("Left Servo Position", leftgripperDrive.getPosition());
            telemetry.addData("Gripper position", gripperPosition);
            telemetry.addData("Gripper 2 position", gripperPosition2);
            telemetry.addData("Left Button Press", lBPress);
            telemetry.addData("Right Button Press", rBPress);
            telemetry.update();
        }
    }
}

