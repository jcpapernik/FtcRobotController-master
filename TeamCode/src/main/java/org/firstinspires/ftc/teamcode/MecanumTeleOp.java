package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
   // Hardware hardwareMap;
   Hardware drive;


    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
       // HardwareMap HardwareMap;

        Hardware drive = new Hardware(hardwareMap);



        // See the note about this earlier on this page.

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanum();
            lift();
            intakeRotation();
            outtake();
            intake();

    }}
    public void lift(){
        if (gamepad1.dpad_up){
            drive.lift.setPower(1);
        }
        else if (gamepad1.dpad_down){
            drive.lift.setPower(-1);
        }
        else {
            drive.lift.setPower(0);
        }
    }
    public void mecanum(){
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double leftFrontPower = (y + x + rx) / denominator;
        double leftRearPower = (y - x + rx) / denominator;
        double rightFrontPower = (y - x - rx) / denominator;
        double rightRearPower = (y + x - rx) / denominator;

        drive.leftFront.setPower(leftFrontPower);
        drive.leftRear.setPower(leftRearPower);
        drive.rightFront.setPower(rightFrontPower);
        drive.rightRear.setPower(rightRearPower);

    }
    public void outtake()
    {
        if(gamepad1.a)
        {
            drive.outtake.setPosition(1);
        }
        else if(gamepad1.b)
        {
            drive.outtake.setPosition(0);
        }

    }
    public void intakeRotation() {
        if(gamepad1.a) {
            drive.intakeRotation.setPosition(1);
        }
        else if(gamepad1.b) {
            drive.intakeRotation.setPosition(0);
        }
    }
    public void intake() {
        if(gamepad1.right_trigger > 0.5) {
            drive.intake.setPower(1);
        }
        else if (gamepad1.left_trigger>0.5) {
            drive.intake.setPower(-1);
        }
        else{
            drive.intake.setPower(0);
        }
    }
    }





