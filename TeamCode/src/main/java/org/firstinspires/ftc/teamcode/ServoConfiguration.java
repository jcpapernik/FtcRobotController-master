package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoConfiguration extends LinearOpMode
{
  public Servo outtake;
  @Override
  public void runOpMode()
  {
    while (opModeIsActive()) {
      outtake.setPosition(0);
    }
  }


}