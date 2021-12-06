package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

public class StinkySingleplayerMode extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        boolean leftTabDown = false;
        boolean rightTabDown = false;

        double intakeDirection = 0.2;
        double leftIntakePower = 0;
        double rightIntakePower = 0;

        boolean bDown = false;

        boolean aDown = false;
        boolean releaserOpen = true;

        //boolean duckMode = false;

        waitForStart();
        while(opModeIsActive()) {

            //mainRobot.setDrivePower(
            //      new Pose2d(gamepad1.left_stick_y,gamepad1.left_stick_x,-gamepad1.right_stick_x));

            mainRobot.tempDrive(-gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);

            if(gamepad1.a) {
                mainRobot.spinner.spin(gamepad1.right_trigger - gamepad1.left_trigger);
            }

            if(gamepad1.left_bumper && !leftTabDown) {
                if(gamepad1.a) {
                    mainRobot.spinner.spin(0.8);
                    leftTabDown = true;
                } else {
                    leftTabDown = true;
                    leftIntakePower = leftIntakePower == 0 ? (gamepad1.left_trigger == 0 ? 1 : -1) * 0.4 : 0;
                    mainRobot.intake.setLeftPower(leftIntakePower);
                }
            }
            if(!gamepad1.left_bumper && leftTabDown) {
                leftTabDown = false;
            }

            if(gamepad1.right_bumper && !rightTabDown) {
                if(gamepad1.a) {
                    mainRobot.spinner.spin(-0.8);
                    rightTabDown = true;
                } else {
                    rightTabDown = true;
                    rightIntakePower = rightIntakePower == 0 ? (gamepad1.right_trigger == 0 ? 1 : -1) * 0.4 : 0;
                    mainRobot.intake.setRightPower(rightIntakePower);
                }
            }
            if(!gamepad1.right_bumper && rightTabDown) {
                rightTabDown = false;
            }








            telemetry.addData("X: ",mainRobot.getPoseEstimate().getX());
            telemetry.addData("Y: ",mainRobot.getPoseEstimate().getY());
            telemetry.addData("Heading: ",mainRobot.getPoseEstimate().getHeading());
            telemetry.addData("Releaser Open? ", releaserOpen);
            telemetry.update();

        }

    }

}
