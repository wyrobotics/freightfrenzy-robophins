package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

public class DrivebaseTele extends LinearOpMode {

    private MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        boolean leftTabDown = false;
        boolean rightTabDown = false;

        double intakeDirection = 1;
        double leftIntakePower = 0;
        double rightIntakePower = 0;

        boolean bDown = false;

        boolean aDown = false;
        boolean releaserOpen = true ;

        mainRobot.setMaxPower(0.5);

        waitForStart();
        while(opModeIsActive()) {

            //mainRobot.setDrivePower(
              //      new Pose2d(gamepad1.left_stick_y,gamepad1.left_stick_x,-gamepad1.right_stick_x));

            //mainRobot.tempDrive(gamepad1.left_stick_x,gamepad1.left_stick_y,-gamepad1.right_stick_x);



            if(gamepad2.left_bumper && !leftTabDown) {
                leftTabDown = true;
                leftIntakePower = leftIntakePower == 0 ? intakeDirection : 0;
                mainRobot.intake.setLeftPower(leftIntakePower);
            }
            if(!gamepad2.left_bumper && leftTabDown) {
                leftTabDown = false;
            }

            if(gamepad2.right_bumper && !rightTabDown) {
                rightTabDown = true;
                rightIntakePower = rightIntakePower == 0 ? intakeDirection : 0;
                mainRobot.intake.setRightPower(rightIntakePower);
            }
            if(!gamepad2.right_bumper && rightTabDown) {
                rightTabDown = false;
            }

            if(gamepad2.b && !bDown) {
                bDown = true;
                intakeDirection *= -1;
            }
            if(!gamepad2.b && bDown) {
                bDown = false;
            }

            if(gamepad1.right_bumper) {
                mainRobot.spinner.spin(0.8);
            } else {
                mainRobot.spinner.spin(gamepad1.right_trigger);
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
