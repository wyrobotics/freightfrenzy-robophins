package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Spinner {

    DcMotor spinner;

    public Spinner(HardwareMap hardwareMap, Telemetry telemetry) {

        spinner = hardwareMap.get(DcMotor.class, "spinner");

        spinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public void spin(double power) { spinner.setPower(power); }

}
