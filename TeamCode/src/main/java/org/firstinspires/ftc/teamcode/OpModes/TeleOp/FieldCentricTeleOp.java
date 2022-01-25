package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@TeleOp
public class FieldCentricTeleOp extends LinearOpMode {

    MainRobot mainRobot;

    @Override
    public void runOpMode() {

        mainRobot = new MainRobot(hardwareMap, telemetry);

        boolean dpadLeft = false;
        boolean dpadRight = false;
        waitForStart();
        while(opModeIsActive()) {

            double heading = mainRobot.getPoseEstimate().getHeading();

            double stickX = gamepad1.left_stick_x, stickY = gamepad1.left_stick_y;

            /*
            mainRobot.setWeightedDrivePower(new Pose2d(
                    -((stickX * Math.sin(heading)) + (stickY * Math.cos(heading))),
                    -((stickX * Math.cos(heading)) - (stickY * Math.sin(heading))),
            -gamepad1.right_stick_x));

             */

            mainRobot.setWeightedDrivePower(new Pose2d(-stickY, -stickX, -gamepad1.right_stick_x));

            if(!gamepad1.b) {

                if (gamepad1.right_bumper) {
                    mainRobot.intake.setRightPower(0.4);
                } else if (gamepad1.right_trigger > 0.05) {
                    mainRobot.intake.setRightPower(-0.4);
                } else {
                    mainRobot.intake.setRightPower(0);
                }

                if (gamepad1.left_bumper) {
                    mainRobot.intake.setLeftPower(0.4);
                } else if (gamepad1.left_trigger > 0.05) {
                    mainRobot.intake.setLeftPower(-0.4);
                } else {
                    mainRobot.intake.setLeftPower(0);
                }

                mainRobot.spinner.spin(0);
                mainRobot.extender.closeReleaser();

            } else {

                if(gamepad1.right_trigger > 0.05) {
                    mainRobot.spinner.spin(0.8);
                } else if(gamepad1.left_trigger > 0.05) {
                    mainRobot.spinner.spin(-0.8);
                } else {
                    mainRobot.spinner.spin(0);
                }

                if(gamepad1.right_bumper) {
                    mainRobot.extender.openReleaser();
                } else {
                    mainRobot.extender.closeReleaser();
                }

            }


            if(gamepad1.x) {
                mainRobot.lifter.drop();
            } else if(gamepad1.y) {
                mainRobot.lifter.lift();
            } else {
                mainRobot.lifter.stop();
            }

            if(gamepad1.a) {
                mainRobot.lifter.slap();
            } else {
                mainRobot.lifter.unslap();
            }

            if(gamepad1.dpad_up) {
                mainRobot.extender.setExtenderPower(0.5);
            } else if(gamepad1.dpad_down) {
                mainRobot.extender.setExtenderPower(-0.5);
            } else {
                mainRobot.extender.setExtenderPower(0);
            }

            if(gamepad1.dpad_left && !dpadLeft) {
                mainRobot.extender.increaseRotatorPosition();
                dpadLeft = true;
            } else if(gamepad1.dpad_right && !dpadRight) {
                mainRobot.extender.decreaseRotatorPosition();
                dpadRight = true;
            }

            if(!gamepad1.dpad_left && dpadLeft) {
                dpadLeft = false;
            }
            if(!gamepad1.dpad_right && dpadRight) {
                dpadRight = false;
            }

            telemetry.addData("Rotator: ", mainRobot.extender.rotatorPosition);
            telemetry.addData("Up Switch Pressed? : ", mainRobot.lifter.upSwitch.isPressed());
            telemetry.addData("X: ", mainRobot.getPoseEstimate().getX());
            telemetry.addData("Y: ", mainRobot.getPoseEstimate().getY());
            telemetry.addData("Heading: ", mainRobot.getPoseEstimate().getHeading());
            telemetry.update();

        }

    }

}
