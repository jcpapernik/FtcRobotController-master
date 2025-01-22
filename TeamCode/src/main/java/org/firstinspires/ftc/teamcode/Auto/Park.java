package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "Auto")
public class Park extends LinearOpMode {

    Pose2d startPose = new Pose2d(36, 60, Math.toRadians(180));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        drive.setPoseEstimate(startPose);


        waitForStart();

        if (isStopRequested()) return;

        TrajectorySequence trajSeq  = drive.trajectorySequenceBuilder(startPose)
                .forward(20)

                .build();

        drive.followTrajectorySequence(trajSeq);


}
}
