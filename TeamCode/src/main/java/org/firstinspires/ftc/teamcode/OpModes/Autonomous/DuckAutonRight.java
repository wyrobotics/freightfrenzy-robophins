package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@Autonomous
public class DuckAutonRight extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();

        mainRobot.setWeightedDrivePower(new Pose2d(0.5,0,0));
        mainRobot.pause(1000);
        mainRobot.setWeightedDrivePower(new Pose2d(0,0,0));

        mainRobot.pause(1000);

        mainRobot.spinner.spin(0.6);
        mainRobot.pause(5000);
        mainRobot.spinner.spin(0);

    }

}
