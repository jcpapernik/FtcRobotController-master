package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware extends DriveConstants {
    public DcMotor leftFront;
    public DcMotor leftRear;
    public DcMotor rightRear;
    public DcMotor rightFront;
    public  DcMotor lift;
    public DcMotor extend;

    public Servo outtake, intakeRotation;

    public Hardware(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        lift = hardwareMap.get(DcMotor.class, "lift");
        extend = hardwareMap.get(DcMotor.class, "extend");
        outtake = hardwareMap.get(Servo.class, "outtake");
        intakeRotation = hardwareMap.get(Servo.class, "intakeRotation");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

    }
}