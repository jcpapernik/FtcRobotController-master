package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp
public class tiefjk extends LinearOpMode {
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
