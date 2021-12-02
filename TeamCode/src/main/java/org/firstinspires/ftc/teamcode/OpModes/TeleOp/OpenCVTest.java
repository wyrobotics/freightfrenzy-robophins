package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@TeleOp
public class OpenCVTest extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        mainRobot.openCVSampler.initOpenCVCamera();

        waitForStart();
        while(opModeIsActive()) {

            telemetry.addData("Pink: ", mainRobot.openCVSampler.getPinkX());
            telemetry.update();

        }

    }

}
