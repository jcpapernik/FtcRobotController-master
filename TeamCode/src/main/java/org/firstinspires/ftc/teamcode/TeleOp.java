package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    Hardware robot;
    SampleMecanumDrive driveo;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.a){
            robot.lift.setPower(1);
            }
            else if (gamepad1.b){
                robot.lift.setPower(-1);

            }


        }
    }
}
