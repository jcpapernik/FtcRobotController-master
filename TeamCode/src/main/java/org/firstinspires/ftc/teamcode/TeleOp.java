package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode
{

     Hardware lift = new Hardware(hardwareMap, hardwareMap);
    // Declare motor variables for the mecanum drivetrain
    // Declare motor variable for the lift mechanism
    public void runOpMode() {
        this.lift = lift;;
        // Wait for the user to press the "Play" button on the driver station
        waitForStart();

        // Main control loop: runs until the "Stop" button is pressed
        while (opModeIsActive()) {

            // **Lift Control**
            // Move the lift up when gamepad1.a is pressed
            if (gamepad1.dpad_up) {
                Hardware.lift.setPower(1); // Move the lift up at full power
            } else if (gamepad1.dpad_down) {
           Hardware.lift.setPower(-1); // Move the lift down at full power
            }

            /*lift.setPower(0); // Stop the lift
            lift.setPower(-1); // Move the lift down at full power
            lift.setPower(0); // Stop the lift*/
        }
}
}
