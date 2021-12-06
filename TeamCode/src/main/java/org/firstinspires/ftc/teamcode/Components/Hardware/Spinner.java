package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Spinner {

    CRServo spinner;

    public Spinner(HardwareMap hardwareMap, Telemetry telemetry) {

        spinner = hardwareMap.get(CRServo.class, "spinner");

    }

    public void spin(double power) { spinner.setPower(power); }

}
