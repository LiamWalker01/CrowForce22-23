package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "RightMin_Autonomous", group = "Drew's Autos" )

public class RightMin_Autonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;

    @Override
    public void runOpMode() throws InterruptedException{

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontleftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backleftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backrightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        double tile = 300;

        ElapsedTime matchTime = new ElapsedTime();
        telemetry.addData("Run Time:", matchTime);
        //moveSimpleEnc(1,0.3,tile);
        moveSimple(30,.5);
        //moveEncoders(90,30,1000);
        //moveEncoders(90,30,1000)
        // moveSimpleEnc(1,0.3, 2*tile);
        //turnSimple(1,0.2,100);

        telemetry.update();

    }

    public void moveGyro(double angle, double speed, double distance) {
    }

    public void turnGyro(double direction, double speed) {
    }

    public void moveEncoders(double angle, double speed, double distance) {
        angle = angle*Math.PI/180;
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double sin = Math.sin(angle - Math.PI / 4);
        double cos = Math.cos(angle - Math.PI / 4);

        while (frontrightDrive.getCurrentPosition() <= distance * sin) {
            frontleftDrive.setPower(speed * sin);
            frontrightDrive.setPower(speed * cos);
            backleftDrive.setPower(speed * cos);
            backleftDrive.setPower(speed * sin);
        }
        if (frontrightDrive.getCurrentPosition() > distance * sin) {
            frontleftDrive.setPower(0);
            frontrightDrive.setPower(0);
            backleftDrive.setPower(0);
            backleftDrive.setPower(0);
        }

    }

    public void turnEncoders(double angle, double speed) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (angle > 0) {
            while (frontrightDrive.getCurrentPosition() <= (angle/360)*348.5) {
                frontleftDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(speed);
                backrightDrive.setPower(speed);

            }

        }
        if (angle < 0) {
            while (frontrightDrive.getCurrentPosition() >= (angle/360)*348.5) {
                frontleftDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
                backrightDrive.setPower(-speed);

            }
        }
    }

    public void moveTime(double angle, double speed, double time) {
        //if (angle >= 0) {angle = angle-180;}
        //if (angle<0) {angle = angle+180;}
        double sin = Math.sin(angle - Math.PI / 4);
        double cos = Math.cos(angle - Math.PI / 4);
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() <= time) {
            if (movementTime.time() < time) {
                frontleftDrive.setPower(speed * cos);
                frontrightDrive.setPower(speed * sin);
                backleftDrive.setPower(speed * sin);
                backleftDrive.setPower(speed * cos);
            } else {
                frontleftDrive.setPower(0);
                frontrightDrive.setPower(0);
                backleftDrive.setPower(0);
                backleftDrive.setPower(0);
            }

        }
    }

    public void turnTime(double direction, double time) {
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() < time*1000) {
            frontleftDrive.setPower(direction);
            frontrightDrive.setPower(direction);
            backleftDrive.setPower(direction);
            backrightDrive.setPower(direction);
        }
    }
    public void moveSimple (double direction, double time) {
        time = time*1;
        ElapsedTime movementTime = new ElapsedTime();
        while (movementTime.time() < time) {
            frontleftDrive.setPower(direction);
            frontrightDrive.setPower(direction);
            backleftDrive.setPower(direction);
            backrightDrive.setPower(direction);
        }
    }
    public void moveSimpleEnc(double direction, double speed, double distance) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        boolean backwards = false;
        if (direction < 0) {
            backwards = true;
        }


        if (backwards = true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
            }

        }

        if (backwards = false) {
            while (frontleftDrive.getCurrentPosition() < distance) {
                frontleftDrive.setPower(speed);
                backrightDrive.setPower(-speed);
                backleftDrive.setPower(-speed);
                frontrightDrive.setPower(speed);
            }

        }
    }
    public void strafeSimple(double direction, double speed, double distance) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        boolean left = false;
        if (direction < 0) {left = true;}

        if (left = true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
            }

        }

        if(left = false) {
            while (frontleftDrive.getCurrentPosition() < distance) {
                frontleftDrive.setPower(speed);
                backrightDrive.setPower(-speed);
                backleftDrive.setPower(-speed);
                frontrightDrive.setPower(speed);
            }
        }
    }
    public void turnSimple(double direction, double speed, double distance) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        boolean left = false;
        if (direction < 0) {left = true;}

        if (left = true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(-speed);
                frontrightDrive.setPower(speed);
            }

        }

        if(left = false) {
            while (frontleftDrive.getCurrentPosition() < distance) {
                frontleftDrive.setPower(speed);
                backrightDrive.setPower(-speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
            }
        }
    }
}

