package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lifter {

    DcMotor lifter;
    Servo slapper;

    public Lifter(HardwareMap hardwareMap, Telemetry telemetry) {

        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slapper = hardwareMap.get(Servo.class, "slapper");

    }

    public void moveLifter(double power) { lifter.setPower(power); }

    public void lift() { moveLifter(0.5); }
    public void drop() { moveLifter(-0.5); }
    public void stop() { moveLifter(0); }

    public void slap() { slapper.setPosition(0.8); }
    public void unslap() { slapper.setPosition(0.2); }

}
