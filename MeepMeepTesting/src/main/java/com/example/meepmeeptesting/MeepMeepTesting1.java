package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting1 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(219.135), Math.toRadians(60), 14)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(180)))

                        .addTemporalMarker(() -> {
                            //intakeRotation.setPosition(.5);
                            // lift.setPower(1);
                        })

                        .lineToLinearHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .addTemporalMarker(() -> {
                            // lift.setPower(0);
                            //outtake.setPosition(1);
                        })

                        .waitSeconds(.5)
                        .addTemporalMarker(() -> {
                            //outtake.setPosition(0);
                            //lift.setPower(-1);
                        })


                        .lineToSplineHeading(new Pose2d(36, 26, Math.toRadians(0)))

                        .addTemporalMarker(()->{
                            //intakeRotation.setPosition(0);
                            //intake.setPower(1)
                        })

                        .lineTo(new Vector2d(44, 26))
                        .addTemporalMarkerOffset(.5,()->{
                            //intakeRotation.setPosition(1);
                            //intake.setPower(0)
                        })
                        .addTemporalMarkerOffset(1,()->{
                            //intake.setPower(-1)
                        })
                        .addTemporalMarkerOffset(2,()->{
                            //intakeRotation.setPosition(.5);
                            // lift.setPower(1);
                        })

                        .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .addTemporalMarker(() -> {
                            // lift.setPower(0);
                            //outtake.setPosition(1);
                        })

                        .waitSeconds(.5)
                        .addTemporalMarker(() -> {
                            //outtake.setPosition(0);
                            //lift.setPower(-1);
                        })
                        .lineToSplineHeading(new Pose2d(48, 26, Math.toRadians(0)))
                        .addTemporalMarker(()->{
                            //intakeRotation.setPosition(0);
                            //intake.setPower(1)
                        })
                        .lineTo(new Vector2d(56, 26))
                        .addTemporalMarkerOffset(.5,()->{
                            //intakeRotation.setPosition(1);
                            //intake.setPower(0)
                        })
                        .addTemporalMarkerOffset(1,()->{
                            //intake.setPower(-1)
                        })
                        .addTemporalMarkerOffset(2,()->{
                            //intakeRotation.setPosition(.5);
                            // lift.setPower(1);
                        })
                        .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .addTemporalMarker(() -> {
                            // lift.setPower(0);
                            //outtake.setPosition(1);
                        })

                        .waitSeconds(.5)
                        .addTemporalMarker(() -> {
                            //outtake.setPosition(0);
                            //lift.setPower(-1);
                        })
                        .lineToSplineHeading(new Pose2d(56, 26, Math.toRadians(0)))
                        .addTemporalMarker(()->{
                            //intakeRotation.setPosition(0);
                            //intake.setPower(1)
                        })
                        .lineTo(new Vector2d(68, 26))
                        .addTemporalMarkerOffset(.5,()->{
                            //intakeRotation.setPosition(1);
                            //intake.setPower(0)
                        })
                        .addTemporalMarkerOffset(1,()->{
                            //intake.setPower(-1)
                        })
                        .lineToSplineHeading(new Pose2d(24, 10, Math.toRadians(0)))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}