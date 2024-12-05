package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * TeleOp program for controlling a robot with mecanum wheels, a lift, and a servo mechanism.
 * This program maps gamepad inputs to specific robot functions, including movement,
 * lift control, and servo operation, with telemetry feedback for debugging.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {

    // Declare motor variables for the mecanum drivetrain
    private DcMotor leftFront, leftRear, rightFront, rightRear;
    // Declare motor variable for the lift mechanism
    private DcMotor lift;
    // Declare servo variable for the outtake mechanism
    private Servo outtake;

    // Conversion factor for lift speed in inches per second (example value, adjust as needed)
    private static final double LIFT_SPEED_INCHES_PER_SECOND = 5.0;
    // Variable to track the total height the lift has moved in inches
    private double totalLiftHeight = 0;

    @Override
    public void runOpMode() {
        // Initialize hardware devices using the hardwareMap
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        lift = hardwareMap.get(DcMotor.class, "lift");
        outtake = hardwareMap.get(Servo.class, "outtake");

        // Set motor directions for mecanum drive (reverse some motors for proper movement)
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to BRAKE for all motors for better control when power is zero
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the user to press the "Play" button on the driver station
        waitForStart();

        // Main control loop: runs until the "Stop" button is pressed
        while (opModeIsActive()) {
            // **Drive Control**
            // Use the left stick for forward/backward and strafing, and the right stick for turning
            double drive = -gamepad1.left_stick_y; // Forward/backward control
            double strafe = gamepad1.left_stick_x; // Strafing control
            double turn = gamepad1.right_stick_x;  // Turning control

            // Calculate motor power for mecanum drive using directional inputs
            //TODO:
            double leftFrontPower = drive + strafe + turn;
            double leftRearPower = drive - strafe + turn;
            double rightFrontPower = drive - strafe - turn;
            double rightRearPower = drive + strafe - turn;

            // Normalize motor powers to ensure none exceed the maximum range
            double maxPower = Math.max(1.0, Math.abs(leftFrontPower));
            leftFrontPower /= maxPower;
            leftRearPower /= maxPower;
            rightFrontPower /= maxPower;
            rightRearPower /= maxPower;

            // Set motor power to each wheel
            leftFront.setPower(leftFrontPower);
            leftRear.setPower(leftRearPower);
            rightFront.setPower(rightFrontPower);
            rightRear.setPower(rightRearPower);

            // **Lift Control**
            // Move the lift up when gamepad1.a is pressed
            if (gamepad1.a) {
                lift.setPower(1); // Move the lift up at full power
                sleep(2000); // Keep lifting for 2 seconds
                lift.setPower(0); // Stop the lift
                totalLiftHeight += LIFT_SPEED_INCHES_PER_SECOND * 2; // Update total lift height
            }
            // Move the lift down when gamepad1.b is pressed
            else if (gamepad1.b) {
                lift.setPower(-1); // Move the lift down at full power
                sleep(2000); // Keep lowering for 2 seconds
                lift.setPower(0); // Stop the lift
                totalLiftHeight -= LIFT_SPEED_INCHES_PER_SECOND * 2; // Update total lift height
            }

            // Prevent the lift height from going negative
            if (totalLiftHeight < 0) totalLiftHeight = 0;

            // **Servo Control**
            // Extend the outtake mechanism when gamepad1.x is pressed
            if (gamepad1.x) {
                outtake.setPosition(1); // Fully extend the servo
            }
            // Retract the outtake mechanism when gamepad1.y is pressed
            else if (gamepad1.y) {
                outtake.setPosition(0); // Fully retract the servo
            }

            // **Telemetry**
            // Display motor power, lift height, and servo position on the driver station
            telemetry.addData("Drive Power (Left Front)", leftFrontPower);
            telemetry.addData("Drive Power (Left Rear)", leftRearPower);
            telemetry.addData("Drive Power (Right Front)", rightFrontPower);
            telemetry.addData("Drive Power (Right Rear)", rightRearPower);
            telemetry.addData("Lift Height (in)", totalLiftHeight);
            telemetry.addData("Servo Position", outtake.getPosition());
            telemetry.update();
        }
    }
}
