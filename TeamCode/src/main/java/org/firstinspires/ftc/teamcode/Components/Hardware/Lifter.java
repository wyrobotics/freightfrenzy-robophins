package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lifter {

    DcMotor lifter;
    Servo slapper;

    public TouchSensor upSwitch;
    public TouchSensor downSwitch;

    public Lifter(HardwareMap hardwareMap, Telemetry telemetry) {

        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slapper = hardwareMap.get(Servo.class, "slapper");

        upSwitch = hardwareMap.get(TouchSensor.class, "upSwitch");
        downSwitch = hardwareMap.get(TouchSensor.class, "downSwitch");

    }

    public void moveLifter(double power) { lifter.setPower(power); }

    public void lift() { moveLifter(upSwitch.isPressed() ? 0 : 0.5); }
    public void drop() { moveLifter(downSwitch.isPressed() ? 0 : -0.5); }
    public void stop() { moveLifter(0); }

    public void slap() { slapper.setPosition(0.3); }
    public void unslap() { slapper.setPosition(0.8); }

}
