package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@Autonomous
public class ForwardSpitLeft extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() throws InterruptedException {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();

        mainRobot.tempDrive(0,0.3,0);

        mainRobot.pause(750);

        mainRobot.tempDrive(0,0,0);

        mainRobot.pause(1000);

    }

}
