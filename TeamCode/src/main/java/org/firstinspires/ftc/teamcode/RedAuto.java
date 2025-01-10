package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "Auto")
public class RedAuto extends LinearOpMode {
    DcMotor lift;
     DcMotor extend;
     Servo outtake, intakeRotation;
     CRServo intake;
    Pose2d startPose = new Pose2d(-40, 64,Math.toRadians(90));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        lift = hardwareMap.get(DcMotor.class, "lift");
        extend = hardwareMap.get(DcMotor.class, "extend");
        outtake = hardwareMap.get(Servo.class, "outtake");
        intakeRotation = hardwareMap.get(Servo.class, "intakeRotation");
        intake = hardwareMap.get(CRServo.class, "intake");

        waitForStart();

        if (isStopRequested()) return;

        TrajectorySequence trajSeq  = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                .lineToSplineHeading(new Pose2d(32, 26, Math.toRadians(0)))
                .lineTo(new Vector2d(44, 26))
                .addDisplacementMarker(() -> {
                    intakeRotation.setPosition(1);
                })
                .addDisplacementMarker(() -> {
                    intake.setPower(1);
                })
                .waitSeconds(1)
                .addDisplacementMarker(() -> {
                    intakeRotation.setPosition(0);
                    intake.setPower(0);
                })
                .addDisplacementMarker(() -> {
                    intake.setPower(-1);
                })


                .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                .lineToSplineHeading(new Pose2d(44, 26, Math.toRadians(0)))
                .lineTo(new Vector2d(56, 26))
                .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                .lineToSplineHeading(new Pose2d(56, 26, Math.toRadians(0)))
                .lineTo(new Vector2d(68, 26))
                .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                .lineToSplineHeading(new Pose2d(24, 0, Math.toRadians(0)))
                .build();

        drive.followTrajectorySequence(trajSeq);


}
}
