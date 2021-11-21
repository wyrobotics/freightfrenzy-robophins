package org.firstinspires.ftc.teamcode.Components;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Components.Hardware.Extender;
import org.firstinspires.ftc.teamcode.Components.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Components.Hardware.Spinner;
import org.firstinspires.ftc.teamcode.Components.Software.OpenCVSampler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Components.Software.RealsenseLocalizer;

public class MainRobot extends SampleMecanumDrive {

    public Extender extender;
    public Intake intake;
    public Spinner spinner;
    public OpenCVSampler openCVSampler;

    public enum Team {RED, BLUE}

    private Team currentTeam = Team.RED;

    public MainRobot(HardwareMap hardwareMap, Telemetry telemetry) {

        super(hardwareMap);
        setLocalizer(new RealsenseLocalizer(hardwareMap, telemetry));

        extender = new Extender(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        spinner = new Spinner(hardwareMap, telemetry);

        openCVSampler = new OpenCVSampler(hardwareMap, telemetry);

    }

    public void setTeam(Team newTeam) { currentTeam = newTeam; }

    public void teleOpDrive(double x, double y, double turn) {

        switch(currentTeam) {
            case RED:
                setDrivePower(new Pose2d(y, x, turn));
                break;
            case BLUE:
                setDrivePower(new Pose2d(y, -x, turn));
        }

    }

    public void pause(long time) {

        long initTime = System.currentTimeMillis();

        while(initTime + time > System.currentTimeMillis()) {

        }

    }

}
