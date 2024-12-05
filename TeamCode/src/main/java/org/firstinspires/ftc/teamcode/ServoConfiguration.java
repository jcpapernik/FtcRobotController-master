package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This class handles the configuration and control of the servo device named "outtake."
 * Ensure that the servo is properly connected and initialized before running this program.
 */
public class ServoConfiguration extends LinearOpMode {

    // Declare the servo object
    private Servo outtake;

    // Define constants for the servo positions
    private static final double MIN_POSITION = 0.0; // Minimum position (fully retracted)
    private static final double MAX_POSITION = 1.0; // Maximum position (fully extended)
    private static final double MID_POSITION = 0.5; // Midpoint position

    @Override
    public void runOpMode() {
        // Initialize the servo object using the configuration name "outtake"
        outtake = hardwareMap.get(Servo.class, "outtake");

        // Optionally set the initial position of the servo to the midpoint
        outtake.setPosition(MID_POSITION);

        // Send telemetry to indicate the servo is ready
        telemetry.addData("Status", "Servo 'outtake' initialized");
        telemetry.update();

        // Wait for the OpMode to start
        waitForStart();

        // Loop while the OpMode is active
        while (opModeIsActive()) {
            if (gamepad1.y) {
                // Move the servo to the minimum position
                outtake.setPosition(MIN_POSITION);
                telemetry.addData("Servo Position", "MIN");
            } else if (gamepad1.x) {
                // Move the servo to the maximum position
                outtake.setPosition(MAX_POSITION);
                telemetry.addData("Servo Position", "MAX");
            } else if (gamepad1.b) {
                // Move the servo to the midpoint position
                outtake.setPosition(MID_POSITION);
                telemetry.addData("Servo Position", "MID");
            }

            // Update telemetry
            telemetry.update();
        }
    }
}
