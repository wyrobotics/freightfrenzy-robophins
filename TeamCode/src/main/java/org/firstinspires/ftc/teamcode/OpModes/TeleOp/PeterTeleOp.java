package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Components.MainRobot;

@TeleOp
public class PeterTeleOp extends LinearOpMode {

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

        //mainRobot.setMaxPower(0.5);

        waitForStart();
        while(opModeIsActive()) {

            //mainRobot.setDrivePower(
            //      new Pose2d(gamepad1.left_stick_y,gamepad1.left_stick_x,-gamepad1.right_stick_x));

            mainRobot.discOrtho(-gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);

            if(gamepad1.right_bumper) {
                mainRobot.intake.setRightPower(0.4);
            } else if(gamepad1.right_trigger > 0.05) {
                mainRobot.intake.setRightPower(-0.4);
            } else {
                mainRobot.intake.setRightPower(0);
            }

            if(gamepad1.left_bumper) {
                mainRobot.intake.setLeftPower(0.4);
            } else if(gamepad1.left_trigger > 0.05) {
                mainRobot.intake.setLeftPower(-0.4);
            } else {
                mainRobot.intake.setLeftPower(0);
            }

            if(gamepad1.a) {
                mainRobot.spinner.spin(0.8);
            } else if(gamepad1.b) {
                mainRobot.spinner.spin(-0.8);
            } else {
                mainRobot.spinner.spin(0);
            }


            telemetry.addData("X: ",mainRobot.getPoseEstimate().getX());
            telemetry.addData("Y: ",mainRobot.getPoseEstimate().getY());
            telemetry.addData("Heading: ",mainRobot.getPoseEstimate().getHeading());
            telemetry.addData("Releaser Open? ", releaserOpen);
            telemetry.update();

        }

    }

}
