package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumTeleOp2driver extends LinearOpMode {
    Hardware robot;

    double speedOffset = .8;
    static final double TICKS_PER_INCH = 153.9710145; // Adjust to match your lift's configuration
    static final int MAX_LIFT_TICKS = (int) (36 * TICKS_PER_INCH); // Max lift extension (in inches)
    static final int MIN_LIFT_TICKS = 0; // Fully retracted
    int liftTargetPosition = 0; // Tracks the lift's current target position

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the robot hardware
        robot = new Hardware(hardwareMap);
        // Ensure the lift motor retracts and encoder is reset
        robot.lift.setPower(-1); // Move lift downward
        sleep(500); // Allow the motor to retract for 0.5 seconds (adjust time as needed)
        robot.lift.setPower(0); // Stop the motor
        robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder
        robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Use encoder mode for teleop



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

        robot.leftFront.setPower(leftFrontPower * speedOffset);
        robot.leftRear.setPower(leftRearPower * speedOffset);
        robot.rightFront.setPower(rightFrontPower * speedOffset);
        robot.rightRear.setPower(rightRearPower * speedOffset);

        telemetry.addData("Speed Offset", speedOffset);
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftRearPower, rightRearPower);
    }

    /**
     * Controls the lift motor.
     */

    public void lift() {
        if (gamepad2.dpad_up && liftTargetPosition < MAX_LIFT_TICKS) {
            liftTargetPosition += (int) (1 * TICKS_PER_INCH); // Increase position by 1 inch
        } else if (gamepad2.dpad_down && liftTargetPosition > MIN_LIFT_TICKS) {
            liftTargetPosition -= (int) (1 * TICKS_PER_INCH); // Decrease position by 1 inch
        }

        // Set the lift to move to the target position
        robot.lift.setTargetPosition(liftTargetPosition);
        robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lift.setPower(1.0); // Adjust power as needed

        // Keep the lift position stable when no input is given
        if (!robot.lift.isBusy()) {
            robot.lift.setPower(0.1); // Maintain slight holding power
        }
        telemetry.addData("Lift Position (Ticks)", robot.lift.getCurrentPosition());
        telemetry.addData("Lift Position (Inches)", robot.lift.getCurrentPosition() / TICKS_PER_INCH);
        telemetry.addData("Lift Target (Inches)", liftTargetPosition / TICKS_PER_INCH);
    }
    /*public void lift() {
        if (gamepad2.dpad_up) {
            robot.lift.setPower(1); // Move lift up
            speedOffset =.6;
            robot.intakeRotation.setPosition(.4);
        } else if (gamepad2.dpad_down) {
            robot.lift.setPower(-1); // Move lift down
            speedOffset =.7;
        } else {
            robot.lift.setPower(0); // Stop the lift
            speedOffset = .8;
        }
    }*/

    /**
     * Controls the extension motor.
     */
    public void extend() {
        if (gamepad2.dpad_right) {
            robot.extend.setPower(1); // Extend
        } else if (gamepad2.dpad_left) {
            robot.extend.setPower(-1); // Retract
        } else {
            robot.extend.setPower(0); // Stop extension
        }
    }

    /**
     * Controls the outtake servo.
     */
    public void outtake() {
        if (gamepad2.x) {
            robot.outtake.setPosition(1); // Set outtake to position 1
        } else if (gamepad2.y) {
            robot.outtake.setPosition(0); // Set outtake to position 0
        }
    }

    /**
     * Controls the intake rotation servo.
     */
    public void intakeRotation() {
        if (gamepad2.a) {
            robot.intakeRotation.setPosition(1); // Set intake rotation to position 1
        } else if (gamepad2.b) {
            robot.intakeRotation.setPosition(0); // Set intake rotation to position 0
        } else if (gamepad2.right_bumper) {
            robot.intakeRotation.setPosition(.4);


        }
    }

    /**
     * Controls the intake motor.
     */
    public void intake() {
        if (gamepad2.right_trigger > 0.5) {
            robot.intake.setPower(1); // Run spit out

        } else if (gamepad2.left_trigger > 0.5) {
            robot.intake.setPower(-1); // Run intake backward
        } else {
            robot.intake.setPower(0); // Stop the intake
        }
    }

}