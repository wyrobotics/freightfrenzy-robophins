package org.firstinspires.ftc.teamcode.Components.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Extender {

    private DcMotor extender;
    private Servo rotator;
    private Servo releaser;

    public TouchSensor inSwitch;
    private TouchSensor outSwitch;

    public double rotatorPosition = 0.3;

    public Extender(HardwareMap hardwareMap, Telemetry telemetry) {

        extender = hardwareMap.get(DcMotor.class, "extender");
        extender.setDirection(DcMotorSimple.Direction.REVERSE);
        rotator = hardwareMap.get(Servo.class, "rotator");
        releaser = hardwareMap.get(Servo.class, "releaser");

        inSwitch = hardwareMap.get(TouchSensor.class, "inSwitch");
        //outSwitch = hardwareMap.get(TouchSensor.class, "outSwitch");

        extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    public void setExtenderPower(double power) {

        double finalPower = power;

        if(inSwitch.isPressed()) { finalPower = Math.max(0, finalPower); }
        //if(outSwitch.getState()) { finalPower = Math.min(0, finalPower); }

        extender.setPower(finalPower);

    }

    public void increaseRotatorPosition() {
        //rotator.setPosition(rotator.getPosition() + 0.00001);
        rotatorPosition = Math.max(rotatorPosition, rotatorPosition + 0.05);
        rotator.setPosition(rotatorPosition);
    }
    public void decreaseRotatorPosition() {
        //rotator.setPosition(rotator.getPosition() - 0.00001);
        rotatorPosition = Math.min(rotatorPosition, rotatorPosition - 0.05);
        rotator.setPosition(rotatorPosition);
    }

    public void openReleaser() { releaser.setPosition(0.6); }
    public void closeReleaser() { releaser.setPosition(0.8); }

}
