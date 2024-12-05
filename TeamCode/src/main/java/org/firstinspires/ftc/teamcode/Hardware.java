package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware extends DriveConstants
{
    public DcMotor leftFront, leftRear, rightRear, rightFront,  lift;
    public Servo outtake;
    public Hardware(HardwareMap hardwareMap)
    {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        lift = hardwareMap.get(DcMotor.class, "lift");
        outtake = hardwareMap.get(Servo.class, "outtake");
// TODO: 12/5/2024

    }
}
