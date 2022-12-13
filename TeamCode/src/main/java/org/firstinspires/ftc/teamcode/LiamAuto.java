package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;




import java.util.ArrayList;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Liam Auto", group = "Linear OpMode" )

public class LiamAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor middleslideDrive = null;
    private Servo rightgripperDrive = null;
    private Servo leftgripperDrive = null;

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
    double tagsize = 0.166;

    int tagPosition = 0;

    int ID_TAG_OF_INTEREST1 = 1; // Tag ID 1 from the 36h11 family
    int ID_TAG_OF_INTEREST2 = 2;
    int ID_TAG_OF_INTEREST3 = 3;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {
        //Motors and Servos
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive= hardwareMap.get(DcMotor.class, "middle_slides_drive");
        rightgripperDrive = hardwareMap.get(Servo.class, "right_gripper_drive");
        leftgripperDrive = hardwareMap.get(Servo.class, "left_gripper_drive");

        frontleftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontrightDrive.setDirection(DcMotor.Direction.REVERSE);
        backleftDrive.setDirection(DcMotor.Direction.FORWARD);
        backrightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightgripperDrive.setDirection(Servo.Direction.REVERSE);
        leftgripperDrive.setDirection(Servo.Direction.FORWARD);
        middleslideDrive.setDirection(DcMotor.Direction.FORWARD);
        middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightgripperDrive.setPosition(gripperStartPositionRight);
        leftgripperDrive.setPosition(gripperStartPositionLeft);

        //Camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;


                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == ID_TAG_OF_INTEREST1)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 1;
                        break;
                    }
                    if(tag.id == ID_TAG_OF_INTEREST2)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 2;
                        break;
                    }
                    if(tag.id == ID_TAG_OF_INTEREST3)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        tagPosition = 3;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight at Position " + tagPosition);
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                }

            }


            telemetry.update();
            sleep(20);
        }

        /* Update the telemetry */



        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag was seen at position " + tagPosition);
            telemetry.addLine("Executing plan " + tagPosition);
            telemetry.update();

            if (tagPosition == 1) {
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
            }

            if (tagPosition == 2) {
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
            }

            if (tagPosition == 3) {
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperStartPositionRight);
                leftgripperDrive.setPosition(gripperStartPositionLeft);
                sleep(1000);
                rightgripperDrive.setPosition(gripperEndPositionRight);
                leftgripperDrive.setPosition(gripperEndPositionLeft);
                sleep(1000);
            }


        }
        else
        {
            telemetry.addLine("No tag available, it was never sighted during the init loop :(");
            telemetry.addLine("Backup plan initiated :)");
            telemetry.update();
        }

        /* Actually do something useful */


        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}


    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        /*telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));*/
    }

}