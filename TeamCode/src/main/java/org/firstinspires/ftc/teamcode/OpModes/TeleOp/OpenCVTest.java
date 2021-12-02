package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;
import org.firstinspires.ftc.teamcode.Components.Software.OpenCVSampler;

@TeleOp
public class OpenCVTest extends LinearOpMode {

    OpenCVSampler openCVSampler;

    @Override
    public void runOpMode() {

        openCVSampler = new OpenCVSampler(hardwareMap, telemetry);

        openCVSampler.initOpenCVCamera();

        waitForStart();
        while(opModeIsActive()) {

            telemetry.addData("Pink: ", openCVSampler.getPinkX());
            telemetry.update();

        }

    }

}
