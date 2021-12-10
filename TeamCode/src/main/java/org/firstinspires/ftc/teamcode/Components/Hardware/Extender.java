package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Extender {

    private DcMotor extender;
    private Servo rotator;
    private Servo releaser;

    private DigitalChannel inSwitch;
    private DigitalChannel outSwitch;

    public Extender(HardwareMap hardwareMap, Telemetry telemetry) {

        extender = hardwareMap.get(DcMotor.class, "extender");
        rotator = hardwareMap.get(Servo.class, "rotator");
        releaser = hardwareMap.get(Servo.class, "releaser");

        inSwitch = hardwareMap.get(DigitalChannel.class, "inSwitch");
        outSwitch = hardwareMap.get(DigitalChannel.class, "outSwitch");

        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void setExtenderPower(double power) {

        double finalPower = power;

        //if(inSwitch.getState()) { finalPower = Math.max(0, finalPower); }
        //if(outSwitch.getState()) { finalPower = Math.min(0, finalPower); }

        extender.setPower(finalPower);

    }

    public void increaseRotatorPosition() {
        //rotator.setPosition(rotator.getPosition() + 0.00001);
        rotator.setPosition(0.7);
    }
    public void decreaseRotatorPosition() {
        //rotator.setPosition(rotator.getPosition() - 0.00001);
        rotator.setPosition(0.3);
    }

    public void openReleaser() { releaser.setPosition(0.0); }
    public void closeReleaser() { releaser.setPosition(0.3); }

}
