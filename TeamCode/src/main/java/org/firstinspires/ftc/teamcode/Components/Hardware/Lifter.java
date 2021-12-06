package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lifter {

    DcMotor lifter;

    public Lifter(HardwareMap hardwareMap, Telemetry telemetry) {

        lifter = hardwareMap.get(DcMotor.class, "lifter");
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void moveLifter(double power) { lifter.setPower(power); }

    public void lift() { moveLifter(0.5); }
    public void drop() { moveLifter(-0.5); }
    public void stop() { moveLifter(0); }

}
