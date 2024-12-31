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
     robot = new Hardware(hardwareMap);
    waitForStart();

    if (isStopRequested()) return;

    while (opModeIsActive()) {
      if(gamepad1.right_trigger > 0.5) {
        robot.intake.setPower(1);
      }
      else if (gamepad1.left_trigger>0.5) {
        robot.intake.setPower(-1);
      }
      else{
        robot.intake.setPower(0);
      }
      if (gamepad1.a) {
        robot.intakeRotation.setPosition(1);
       }
      if (gamepad1.b) {
        robot.intakeRotation.setPosition(0);
      }
    }

  }
}