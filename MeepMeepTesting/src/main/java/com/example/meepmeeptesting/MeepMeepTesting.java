package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 30, Math.toRadians(230.2696), Math.toRadians(60), 12.82)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(180)))
                        //.splineTo(new Vector2d(12, 30), 0)
                        //.forward(30)
                        .lineToLinearHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .lineToSplineHeading(new Pose2d(32, 26, Math.toRadians(0)))
                        .lineTo(new Vector2d(44, 26))
                        .addDisplacementMarker(() -> {
                           // intakeRotation.setPosition(1);
                        })
                        .addDisplacementMarker(() -> {
                          //  intake.setPower(1);
                        })
                        .waitSeconds(1)
                        .addDisplacementMarker(() -> {
                            //intakeRotation.setPosition(0);
                          //  intake.setPower(0);
                        })
                        .addDisplacementMarker(() -> {
                            //intake.setPower(-1);
                        })

                        .waitSeconds(1)
                       /* .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .lineToSplineHeading(new Pose2d(44, 26, Math.toRadians(0)))
                        .lineTo(new Vector2d(56, 26))
                        .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .lineToSplineHeading(new Pose2d(56, 26, Math.toRadians(0)))
                        .lineTo(new Vector2d(68, 26))
                        .lineToSplineHeading(new Pose2d(58, 58, Math.toRadians(-135)))
                        .lineToSplineHeading(new Pose2d(24, 0, Math.toRadians(0)))*/

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}