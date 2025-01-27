package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "Auto")
public class Auto extends LinearOpMode {
    DcMotor lift;
     DcMotor extend;
     Servo outtake, intakeRotation;
     CRServo intake;
    Pose2d startPose = new Pose2d(36, 60, Math.toRadians(180));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Hardware initialization
        lift = hardwareMap.get(DcMotor.class, "lift");
        extend = hardwareMap.get(DcMotor.class, "extend");
        outtake = hardwareMap.get(Servo.class, "outtake");
        intakeRotation = hardwareMap.get(Servo.class, "intakeRotation");
        intake = hardwareMap.get(CRServo.class, "intake");
        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        drive.setPoseEstimate(startPose);


        waitForStart();

        if (isStopRequested()) return;

        TrajectorySequence trajSeq  = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    intakeRotation.setPosition(.5);
                     lift.setPower(.8);
                })
                .strafeLeft(4)

                .lineToLinearHeading(new Pose2d(53, 64, Math.toRadians(-135)))
                .addTemporalMarker(() -> {
                    outtake.setPosition(1);
                    lift.setPower(.1);
                })

                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    outtake.setPosition(0);
                    lift.setPower(-1);
                })


                .lineToSplineHeading(new Pose2d(5, 24, Math.toRadians(0)))

                .addTemporalMarker(()->{
                    intakeRotation.setPosition(1);
                    intake.setPower(-1);
                })

                .lineTo(new Vector2d(20, 24))


                .UNSTABLE_addTemporalMarkerOffset(.7,()->{
                    intakeRotation.setPosition(0);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.5,()->{
                    intake.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(2.5,()->{
                    intakeRotation.setPosition(.5);
                     lift.setPower(.8);
                    intake.setPower(0);

                })
                .back(5)

                .lineToSplineHeading(new Pose2d(52, 65, Math.toRadians(-135)))
                .addTemporalMarker(() -> {
                    outtake.setPosition(1);


                })
                .waitSeconds(.2)
                .addTemporalMarker(() -> {
                    lift.setPower(0);

                })

                .waitSeconds(.5)
                .addTemporalMarker(() -> {
                    outtake.setPosition(0);
                    lift.setPower(-1);
                })
                .lineToSplineHeading(new Pose2d(15, 24, Math.toRadians(0)))
                .addTemporalMarker(()->{
                    intakeRotation.setPosition(1);
                    intake.setPower(-1);
                })
                .lineTo(new Vector2d(25, 24))
                .UNSTABLE_addTemporalMarkerOffset(.7,()->{
            intakeRotation.setPosition(0);
        })
                .UNSTABLE_addTemporalMarkerOffset(1.5,()->{
                    intake.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(2.5,()->{
                    intakeRotation.setPosition(.5);
                    lift.setPower(.8);
                    intake.setPower(0);

                })
                .back(5)
               .lineToSplineHeading(new Pose2d(53, 64, Math.toRadians(-135)))
                .addTemporalMarker(() -> {
                     lift.setPower(1);
                    outtake.setPosition(1);
                })

                .waitSeconds(.5)
                .addTemporalMarker(() -> {
                    outtake.setPosition(0);
                    lift.setPower(-1);
                })
                .waitSeconds(2)
                /*.lineToSplineHeading(new Pose2d(56, 26, Math.toRadians(0)))
                .addTemporalMarker(()->{
                    //intakeRotation.setPosition(0);
                    //intake.setPower(1)
                })
                .lineTo(new Vector2d(68, 26))
                .UNSTABLE_addTemporalMarkerOffset(.5,()->{
                    //intakeRotation.setPosition(1);
                    //intake.setPower(0)
                })
                .UNSTABLE_addTemporalMarkerOffset(1,()->{
                    //intake.setPower(-1)
                })
                .lineToSplineHeading(new Pose2d(24, 10, Math.toRadians(0)))*/

                .build();

        drive.followTrajectorySequence(trajSeq);


}
}
