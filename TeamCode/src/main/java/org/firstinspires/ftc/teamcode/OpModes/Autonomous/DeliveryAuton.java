package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

public class DeliveryAuton extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();

        mainRobot.openCVSampler.initOpenCVCamera();

        mainRobot.pause(2000);

        double markerPos = mainRobot.openCVSampler.getPinkX(3000);

        mainRobot.extender.setExtenderPower(0.5);
        mainRobot.pause(1500);
        mainRobot.extender.setExtenderPower(0);
        mainRobot.pause(1000);

        mainRobot.extender.increaseRotatorPosition();

        if(markerPos > 70) { mainRobot.extender.increaseRotatorPosition(); }
        if(markerPos > 140) { mainRobot.extender.increaseRotatorPosition(); }

        mainRobot.setWeightedDrivePower(new Pose2d(0, 0.3, 0));
        mainRobot.pause(1000);
        mainRobot.setWeightedDrivePower(new Pose2d(0,0,0));
        mainRobot.pause(1000);

        mainRobot.extender.openReleaser();

    }

}
