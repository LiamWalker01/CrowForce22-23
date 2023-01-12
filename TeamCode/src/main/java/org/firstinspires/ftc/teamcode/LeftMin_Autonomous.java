package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "LeftMin_Autonomous", group = "Drew's Auto" )



public class LeftMin_Autonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;
    private DcMotor middleslideDrive = null;
    private Servo rightgripperDrive = null;
    private Servo leftgripperDrive = null;
    @Override
    public void runOpMode() throws InterruptedException{

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        middleslideDrive = hardwareMap.get(DcMotor.class, "middle_slides_drive");
        frontleftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontrightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backleftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backrightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        middleslideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        double tile = 1000;

        moveSimpleEnc(1, .5, 1);
        ElapsedTime matchTime = new ElapsedTime();
        telemetry.addData("Run Time:", matchTime);

        //moveSimple(30,1);
        //moveEncoders(90,0.5,1000);
        //moveEncoders(90,30,1000);

        telemetry.update();

    }

    public void moveGyro(double angle, double speed, double distance) {
    }

    public void turnGyro(double direction, double speed) {
    }

    public void moveEncoders(double angle, double speed, double distance) {
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double theta = -angle + 90;
        //theta = angle;
        theta = theta + 45;
        theta = theta*Math.PI/180;

        double y_distance = Math.sin(theta)*distance;
        double x_distance = Math.cos(theta)*distance;
        double y_speed = Math.sin(theta)*speed;
        double x_speed = Math.cos(theta)*speed;
        int y_directionCoefficient = 1;
        int x_directionCoefficient = 1;
        if (y_distance < 0) {y_directionCoefficient = -1;}
        if (x_distance < 0) {x_directionCoefficient = -1;}
        double lFpos = y_directionCoefficient*frontleftDrive.getCurrentPosition();
        double lBpos = x_directionCoefficient*backleftDrive.getCurrentPosition();
        double rFpos = x_directionCoefficient*frontrightDrive.getCurrentPosition();
        double rBpos = y_directionCoefficient*backrightDrive.getCurrentPosition();
        telemetry.addData("frontRight",frontrightDrive.getCurrentPosition());
        telemetry.addData("y_distance: ", y_distance);
        telemetry.addData("x_distance: ", x_distance);
        while (lFpos < y_distance && lBpos<x_distance && rBpos < y_distance && rFpos < x_distance ) {
            frontleftDrive.setPower(y_speed);
            frontrightDrive.setPower(x_speed);
            backleftDrive.setPower(x_speed);
            backrightDrive.setPower(y_speed);
             lFpos = y_directionCoefficient*frontleftDrive.getCurrentPosition();
           lBpos = x_directionCoefficient*backleftDrive.getCurrentPosition();
           rFpos = x_directionCoefficient*frontrightDrive.getCurrentPosition();
           rBpos = y_directionCoefficient*backrightDrive.getCurrentPosition();
            telemetry.update();

        }

        if (lFpos >= y_distance && lBpos >= x_distance && rBpos >= y_distance && rFpos >= x_distance ) {
            frontleftDrive.setPower(y_speed);
            frontrightDrive.setPower(x_speed);
            backleftDrive.setPower(x_speed);
            backrightDrive.setPower(y_speed);
        }





        /*angle = angle*Math.PI/180;
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
*/
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
    public void setSlider(double level) {
        //middleslideDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //middleslideDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double position = 0;
        if (level == 1) {position = 70;}
        if (level == 2) {position = 2000;}
        if (level == 3) {position = 4000;}
        if (middleslideDrive.getCurrentPosition() < position) {
            while (middleslideDrive.getCurrentPosition()< position) {middleslideDrive.setPower(0.3);}

        }
        if (middleslideDrive.getCurrentPosition() > position) {
            while (middleslideDrive.getCurrentPosition()> position) {middleslideDrive.setPower(-0.3);}
        }

    }
    public void moveSimpleEnc(double direction, double speed, double distance) {
        distance = distance*1745.45455;
        frontleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        boolean backwards = false;
        if (direction < 0) {
            backwards = true;
        }


        if (backwards == true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
            }

        }

        if (backwards == false) {
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
        if (direction < 0) {
            left = true;
        }

        if (left = true) {
            while (frontleftDrive.getCurrentPosition() > -distance) {
                frontleftDrive.setPower(-speed);
                backrightDrive.setPower(speed);
                backleftDrive.setPower(speed);
                frontrightDrive.setPower(-speed);
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