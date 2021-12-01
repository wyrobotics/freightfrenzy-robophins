package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@TeleOp
public class FieldCentricTeleOp extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();
        while(opModeIsActive()) {

            double heading = mainRobot.getPoseEstimate().getHeading();

            double stickX = gamepad1.left_stick_x, stickY = gamepad1.left_stick_y;

            mainRobot.discOrtho(
                    (stickX * Math.cos(-heading)) - (stickY * Math.sin(-heading)),
                    (stickX * Math.sin(-heading)) + (stickY * Math.cos(-heading)),
            gamepad1.right_stick_x);

        }

    }

}
