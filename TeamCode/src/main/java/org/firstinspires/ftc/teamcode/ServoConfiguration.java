package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoConfiguration extends LinearOpMode
{
  Hardware robot;

  @Override
  public void runOpMode() {
    Hardware robot = new Hardware(hardwareMap);
    waitForStart();

    if (isStopRequested()) return;

    while (opModeIsActive()) {
      if (gamepad1.a) {
        robot.outtake.setPosition(0);
      }
      if (gamepad1.b) {
        robot.intakeRotation.setPosition(0);
      }
    }
  }
}