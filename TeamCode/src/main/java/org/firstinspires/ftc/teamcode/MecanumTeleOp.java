package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    Hardware robot;
    //double speedOffset = .8;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the robot hardware
        robot = new Hardware(hardwareMap);



        waitForStart();

        if (isStopRequested()) return;

        // Main teleop loop
        while (opModeIsActive()) {

            mecanum();
            lift();
            extend();
            outtake();
            intakeRotation();
            intake();
            telemetry.update();


        }
    }

    /**
     * Controls the mecanum drive.
     */
    public void mecanum() {
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

        robot.leftFront.setPower(leftFrontPower);// * speedOffset);
        robot.leftRear.setPower(leftRearPower);// * speedOffset);
        robot.rightFront.setPower(rightFrontPower);// * speedOffset);
        robot.rightRear.setPower(rightRearPower);// * speedOffset);


        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftRearPower, rightRearPower);
    }

    /**
     * Controls the lift motor.
     */
    public void lift() {
        if (gamepad1.dpad_up) {
            robot.lift.setPower(1); // Move lift up
           // speedOffset =.6;
            robot.intakeRotation.setPosition(.4);
        } else if (gamepad1.dpad_down) {
            robot.lift.setPower(-1); // Move lift down
           // speedOffset =.7;
        } else {
            robot.lift.setPower(0); // Stop the lift
           // speedOffset = 1;
        }
    }

    /**
     * Controls the extension motor.
     */
    public void extend() {
        if (gamepad1.dpad_right) {
            robot.extend.setPower(1); // Extend
        } else if (gamepad1.dpad_left) {
            robot.extend.setPower(-1); // Retract
        } else {
            robot.extend.setPower(0); // Stop extension
        }
    }

    /**
     * Controls the outtake servo.
     */
    public void outtake() {
        if (gamepad1.x) {
            robot.outtake.setPosition(1); // Set outtake to position 1
        } else if (gamepad1.y) {
            robot.outtake.setPosition(0); // Set outtake to position 0
        }
    }

    /**
     * Controls the intake rotation servo.
     */
    public void intakeRotation() {
        if (gamepad1.a) {
            robot.intakeRotation.setPosition(1); // Set intake rotation to position 1
        } else if (gamepad1.b) {
            robot.intakeRotation.setPosition(0); // Set intake rotation to position 0
        } else if (gamepad1.right_bumper) {
            robot.intakeRotation.setPosition(.4);


        }
    }

    /**
     * Controls the intake motor.
     */
    public void intake() {
        if (gamepad1.right_trigger > 0.5) {
            robot.intake.setPower(1); // Run spit out

        } else if (gamepad1.left_trigger > 0.5) {
            robot.intake.setPower(-1); // Run intake backward
        } else {
            robot.intake.setPower(0); // Stop the intake
        }
    }



}