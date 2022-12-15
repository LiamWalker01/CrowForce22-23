package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;


import java.util.ArrayList;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Liam Auto", group = "Liam's Auto" )

public class LiamAuto extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private DcMotor middleSlideDrive = null;

    double gripperStartPositionLeft = .9;
    double gripperStartPositionRight = .9;
    double gripperEndPositionLeft = 1;
    double gripperEndPositionRight = 1;

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagSize = 0.166;

    int tagPosition = 0;

    int tagOfInterest1 = 1; // Tag ID 1 from the 36h11 family
    int tagOfInterest2 = 2; // Tag ID 2 from the 36h11 family
    int tagOfInterest3 = 3; // Tag ID 3 from the 36h11 family

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {
        // Motors and Servos
        DcMotor frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        DcMotor frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        DcMotor backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        DcMotor backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleSlideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");

        Servo rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        Servo leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");

        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightgripperDrive.setDirection(Servo.Direction.REVERSE);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);
        middleSlideDrive.setDirection(DcMotor.Direction.FORWARD);
        middleSlideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleSlideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        middleSlideDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightgripperDrive.setPosition(gripperStartPositionRight);
        leftgripperDrive.setPosition(gripperStartPositionLeft);

        //Camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }

            @Override
            public void onError(int errorCode)
            {
                // does nothing if error
            }
        });

        telemetry.setMsTransmissionInterval(50);

        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections) {
                    if(tag.id == tagOfInterest1) {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 1;
                        break;
                    }

                    if(tag.id == tagOfInterest2) {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 2;
                        break;
                    }

                    if(tag.id == tagOfInterest3) {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 3;
                        break;
                    }
                }

                if(tagFound) { telemetry.addLine("Tag of interest is in sight at Position " + tagPosition); }
                else { telemetry.addLine("Don't see tag of interest :("); }
            }
            telemetry.update();
            sleep(20);  // is required or else system will break
        }

        if(tagOfInterest != null) {
            telemetry.addLine("Tag was seen at position " + tagPosition);
            telemetry.addLine("Executing plan " + tagPosition);
            telemetry.update();

            if (tagPosition == 1) { location1(); }

            if (tagPosition == 2) { location2(); }

            if (tagPosition == 3) { location3(); }

        } else {
            telemetry.addLine("No tag available, it was never seen during the init loop :(");
            telemetry.addLine("Backup plan INITIATED :)");
            location0();
            telemetry.update();
        }
    }

    public void location0() { // if no april tag is detected

    }
    public void location1() {

    }
    public void location2() {

    }
    public void location3() {

    }
    public void setSlider(double level) {
        double position = 0;
        if (level == 1) {position = 70;}
        if (level == 2) {position = 2000;}
        if (level == 3) {position = 4000;}
        if (middleSlideDrive.getCurrentPosition() < position) {
            while (middleSlideDrive.getCurrentPosition() < position) {
                middleSlideDrive.setPower(0.3);}
        }
        if (middleSlideDrive.getCurrentPosition() > position) {
            while (middleSlideDrive.getCurrentPosition() > position) {
                middleSlideDrive.setPower(-0.3);}
        }

    }

}