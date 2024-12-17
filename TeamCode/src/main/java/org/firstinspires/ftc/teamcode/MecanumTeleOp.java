package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
   Hardware robot;

    @Override
    public void runOpMode() throws InterruptedException {
        Hardware robot = new Hardware(hardwareMap);

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
            robot.lift.setPower(1);
        }
        else if (gamepad1.dpad_down){
            robot.lift.setPower(-1);
        }
        else {
            robot.lift.setPower(0);
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

        robot.leftFront.setPower(leftFrontPower);
        robot.leftRear.setPower(leftRearPower);
        robot.rightFront.setPower(rightFrontPower);
        robot.rightRear.setPower(rightRearPower);

    }
    public void outtake()
    {
        if(gamepad1.a)
        {
            robot.outtake.setPosition(1);
        }
        else if(gamepad1.b)
        {
            robot.outtake.setPosition(0);
        }

    }
    public void intakeRotation() {
        if(gamepad1.a) {
            robot.intakeRotation.setPosition(1);
        }
        else if(gamepad1.b) {
            robot.intakeRotation.setPosition(0);
        }
    }
    public void intake() {
        if(gamepad1.right_trigger > 0.5) {
            robot.intake.setPower(1);
        }
        else if (gamepad1.left_trigger>0.5) {
            robot.intake.setPower(-1);
        }
        else{
            robot.intake.setPower(0);
        }
    }
    }




