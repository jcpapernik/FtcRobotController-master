package org.firstinspires.ftc.teamcode.JACOBcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.fazecast.jSerialComm.SerialPort;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "HuskyLens Color Detection", group = "HuskyLens")
public class Main extends LinearOpMode {
    private HuskyLensLibrary hLibrary;
    private Servo intakeServo;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize servo
        intakeServo = hardwareMap.get(Servo.class, "intake");

        // Initialize HuskyLens
        SerialPort[] serialDevices = SerialPort.getCommPorts();
        for (SerialPort device : serialDevices) {
            if (device.getDescriptivePortName().contains("CP210x")) {
                hLibrary = new HuskyLensLibrary(device, 3000000);
                break;
            }
        }

        telemetry.addData("Status", hLibrary != null ? "HuskyLens Initialized" : "HuskyLens Not Found");
        telemetry.update();

        waitForStart();

        if (hLibrary == null) {
            telemetry.addData("Error", "HuskyLens could not be initialized!");
            telemetry.update();
            return;
        }

        while (opModeIsActive()) {
            hLibrary.processHuskyLensData(); // Process data from HuskyLens

            // Check for target color
            int targetColorID = 123; // Replace with the actual ID for the color to detect
            if (isColorDetected(targetColorID)) {
                startServo(); // Spin the servo
                telemetry.addData("Intake", "Running");
            } else {
                stopServo(); // Stop the servo
                telemetry.addData("Intake", "Stopped");
            }

            telemetry.update();
        }
    }

    private boolean isColorDetected(int colorID) {
        try {
            // Check HuskyLens blocks for the target color ID
            for (Block block : hLibrary.getBlocks()) { // Assuming getBlocks() retrieves detected blocks
                if (block.getId() == colorID) {
                    return true;
                }
            }
        } catch (Exception e) {
            telemetry.addData("Error", e.getMessage());
        }
        return false;
    }

    private void startServo() {
        intakeServo.setPosition(1.0); // Adjust based on your servo's configuration
    }

    private void stopServo() {
        intakeServo.setPosition(0.0); // Adjust based on your servo's configuration
    }
}
