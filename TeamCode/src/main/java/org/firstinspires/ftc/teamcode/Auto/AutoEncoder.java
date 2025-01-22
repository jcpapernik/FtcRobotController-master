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

public class AutoEncoder extends LinearOpMode {
    DcMotor lift, extend;
    Servo outtake, intakeRotation;
    CRServo intake;
    Pose2d startPose = new Pose2d(36, 60, Math.toRadians(180));

    // Motor configuration constants
    static final double TICKS_PER_INCH = 159.2; // Calculated
    static final int MAX_LIFT_TICKS = (int) (38.4 * TICKS_PER_INCH); // Max extension
    static final int MIN_LIFT_TICKS = 0; // Fully retracted
    static final double COUNTS_PER_INCH = TICKS_PER_INCH;

    double liftTargetPosition = 0; // Variable to store target position in inches

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

        // Configure lift motor
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Initial robot position
        drive.setPoseEstimate(startPose);

        // Define target heights in inches
        int LIFT_HIGH = 36; // Example: 36 inches
        int LIFT_LOW = 0;

        // Define the trajectory sequence (moved here before waitForStart)
        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    intakeRotation.setPosition(0.5);
                    liftTargetPosition = LIFT_HIGH; // Set target for high lift
                })
                .strafeLeft(4)
                .lineToLinearHeading(new Pose2d(53, 64, Math.toRadians(-135)))
                .addTemporalMarker(() -> {
                    outtake.setPosition(1);
                    liftTargetPosition = LIFT_HIGH; // Keep lift at high position
                })
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    outtake.setPosition(0);
                    liftTargetPosition = LIFT_LOW; // Lower lift
                })
                .lineToSplineHeading(new Pose2d(5, 24, Math.toRadians(0)))
                .addTemporalMarker(() -> {
                    intakeRotation.setPosition(1);
                    intake.setPower(-1);
                })
                .lineTo(new Vector2d(20, 24))
                .UNSTABLE_addTemporalMarkerOffset(0.7, () -> {
                    intakeRotation.setPosition(0);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.5, () -> {
                    intake.setPower(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(2.5, () -> {
                    intakeRotation.setPosition(0.5);
                    liftTargetPosition = LIFT_HIGH; // Set to high lift again
                    intake.setPower(0);
                })
                .back(5)
                .lineToSplineHeading(new Pose2d(52, 65, Math.toRadians(-135)))
                .addTemporalMarker(() -> {
                    outtake.setPosition(1);
                    liftTargetPosition = LIFT_HIGH; // Keep lift at high position
                })
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {
                    lift.setPower(0); // Ensure the motor stops
                })
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    outtake.setPosition(0);
                    liftTargetPosition = LIFT_LOW; // Lower lift to its lowest point
                })
                .build();

        waitForStart(); // Wait for the start of the match

        if (isStopRequested()) return;

        // Execute trajectory asynchronously
        drive.followTrajectorySequenceAsync(trajSeq);

        while (opModeIsActive()) {
            drive.update(); // Update the drive while following the trajectory
            moveLiftToPosition(liftTargetPosition, 0.8); // Simultaneously move lift
            Pose2d poseEstimate = drive.getPoseEstimate();


            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", Math.toDegrees(poseEstimate.getHeading()));

            // Add telemetry for lift position
            telemetry.addData("Ticks", lift.getCurrentPosition());
            telemetry.update();
        }
    }

    private void moveLiftToPosition(double targetPosition, double power) {
        int newMotorTarget = (int) (targetPosition * COUNTS_PER_INCH);

        lift.setTargetPosition(newMotorTarget);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(Math.abs(power));

        if (!lift.isBusy()) {
            lift.setPower(0.1); // Adjusted to 0.1 instead of 0 for slight power
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
