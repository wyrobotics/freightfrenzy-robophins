package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@Autonomous
public class T265Test extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        Pose2d initPose = new Pose2d(0, 0, 0);

        waitForStart();

        mainRobot.setPoseEstimate(new Pose2d(0,0,0));

        while(opModeIsActive()) {

            Trajectory mainTrajectory = mainRobot.trajectoryBuilder(initPose)
                    .splineToConstantHeading(new Vector2d(10,10), 0)
                    .build();

            mainRobot.followTrajectory(mainTrajectory);

            telemetry.addData("x: ", mainRobot.getPoseEstimate().getX());
            telemetry.addData("y: ", mainRobot.getPoseEstimate().getY());
            telemetry.addData("t: ", mainRobot.getPoseEstimate().getHeading());

            telemetry.update();

        }

    }

}
