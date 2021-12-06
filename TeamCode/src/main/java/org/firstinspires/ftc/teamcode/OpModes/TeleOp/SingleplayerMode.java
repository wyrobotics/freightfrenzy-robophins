package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

public class SingleplayerMode extends LinearOpMode {

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
        boolean releaserOpen = true ;

        waitForStart();
        while(opModeIsActive()) {

            //mainRobot.setDrivePower(
            //      new Pose2d(gamepad1.left_stick_y,gamepad1.left_stick_x,-gamepad1.right_stick_x));

            mainRobot.tempDrive(-gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);

            if(gamepad1.dpad_left && !leftTabDown) {
                leftTabDown = true;
                leftIntakePower = leftIntakePower == 0 ? intakeDirection : 0;
                mainRobot.intake.setLeftPower(leftIntakePower);
            }
            if(!gamepad1.dpad_left && leftTabDown) {
                leftTabDown = false;
            }

            if(gamepad1.dpad_right && !rightTabDown) {
                rightTabDown = true;
                rightIntakePower = rightIntakePower == 0 ? intakeDirection : 0;
                mainRobot.intake.setRightPower(rightIntakePower);
            }
            if(!gamepad1.dpad_right && rightTabDown) {
                rightTabDown = false;
            }

            if(gamepad1.b && !bDown) {
                bDown = true;
                intakeDirection *= -1;
                rightIntakePower = rightIntakePower == 0 ? 0 : intakeDirection;
                leftIntakePower = leftIntakePower == 0 ? 0 : intakeDirection;
                mainRobot.intake.setRightPower(rightIntakePower);
                mainRobot.intake.setLeftPower(leftIntakePower);
            }
            if(!gamepad1.b && bDown) {
                bDown = false;
            }

            if(gamepad1.right_bumper) {
                mainRobot.spinner.spin(0.8);
            } else if(gamepad1.left_bumper) {
                mainRobot.spinner.spin(-0.8);
            } else {
                mainRobot.spinner.spin(gamepad1.right_trigger - gamepad1.left_trigger);
            }



            //mainRobot.extender.setExtenderPower(
            //      (gamepad1.dpad_left ? 1 : 0) - (gamepad1.dpad_right ? 1 : 0));

            if(gamepad1.dpad_down) {
                mainRobot.extender.increaseRotatorPosition();
            } else if(gamepad1.dpad_up) {
                mainRobot.extender.decreaseRotatorPosition();
            }

            if(gamepad1.a && !aDown) {
                aDown = true;
                if(releaserOpen) {
                    mainRobot.extender.closeReleaser();
                    releaserOpen = false;
                } else {
                    mainRobot.extender.openReleaser();
                    releaserOpen = true;
                }
            }
            if(!gamepad1.a && aDown) {
                aDown = false;
            }


            telemetry.addData("X: ",mainRobot.getPoseEstimate().getX());
            telemetry.addData("Y: ",mainRobot.getPoseEstimate().getY());
            telemetry.addData("Heading: ",mainRobot.getPoseEstimate().getHeading());
            telemetry.addData("Releaser Open? ", releaserOpen);
            telemetry.update();

        }

    }

}
