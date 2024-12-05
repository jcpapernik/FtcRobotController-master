package org.firstinspires.ftc.teamcode;

public class ServoCalibration {
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
