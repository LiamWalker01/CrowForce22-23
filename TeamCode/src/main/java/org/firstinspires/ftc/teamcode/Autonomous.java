package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous", group = "Linear OpMode" )



public class Autonomous extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontleftDrive = null;
    private DcMotor frontrightDrive = null;
    private DcMotor backleftDrive = null;
    private DcMotor backrightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontleftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontrightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backleftDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        backrightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        /* moveGyro( 0, 30, 2);

         */
    }
    public void moveGyro(double angle, double speed, double distance) {}
    public void turnGyro(double direction, double speed) {}
    public void moveEncoders(double angle, double speed, double distance) {}
    public void turnEncoders(double direction, double speed) {}
    public void moveTime(double angle, double speed, double time) {
        double sin = Math.sin(angle-Math.PI/4);
        double cos = Math.cos(angle-Math.PI/4);
        ElapsedTime movementTime = new ElapsedTime();

        while (movementTime.time() < time) {
            frontleftDrive.setPower(speed * cos);
            frontrightDrive.setPower(speed * sin);
            backleftDrive.setPower(speed * sin);
            backleftDrive.setPower(speed * cos);
        }
    }
    public void turnTime(double direction) {}

}
