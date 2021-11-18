package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@TeleOp
public class DrivebaseTele extends LinearOpMode {

    private MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        waitForStart();
        while(opModeIsActive()) {

            mainRobot.setDrivePower(
                    new Pose2d(gamepad1.left_stick_x,gamepad1.left_stick_y,-gamepad1.right_stick_x));

            telemetry.addData("X: ",mainRobot.getPoseEstimate().getX());
            telemetry.addData("Y: ",mainRobot.getPoseEstimate().getY());
            telemetry.addData("Heading: ",mainRobot.getPoseEstimate().getHeading());
            telemetry.update();

        }

    }

}
