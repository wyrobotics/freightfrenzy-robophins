package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

public class DuckAutonBlue extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() throws InterruptedException {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();

        mainRobot.tempDrive(0,-0.3,0);

        wait(1000);

        mainRobot.tempDrive(0,0,0);

        wait(500);

        mainRobot.spinner.spin(-0.5);

        wait(3000);

        mainRobot.spinner.spin(0);

        wait(1000);

    }

}
