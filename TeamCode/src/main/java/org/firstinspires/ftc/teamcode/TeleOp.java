package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    Hardware robot;
    SampleMecanumDrive drive;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            robot.fl.setPower(1);
        }
    }
}
