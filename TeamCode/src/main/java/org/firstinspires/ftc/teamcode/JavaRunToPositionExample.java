package org.firstinspires.ftc.teamcode;

// Import statements
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class JavaRunToPositionExample extends LinearOpMode {

    DcMotorEx motor;

    @Override
    public void runOpMode() {

        // Initialize the motor using the hardware map
        motor = hardwareMap.get(DcMotorEx.class, "Motor");

        // Reset the encoder during initialization
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Wait for the start of the Op Mode
        waitForStart();

        // Set the motor's target position to 300 ticks
        motor.setTargetPosition(300);

        // Switch to RUN_TO_POSITION mode
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Start the motor moving by setting the max velocity to 200 ticks per second
        motor.setVelocity(200);

        // While the Op Mode is running, show the motor's status via telemetry
        while (opModeIsActive()) {
            telemetry.addData("velocity", motor.getVelocity());
            telemetry.addData("position", motor.getCurrentPosition());
            telemetry.addData("is at target", !motor.isBusy());
            telemetry.update();
        }
    }
}
