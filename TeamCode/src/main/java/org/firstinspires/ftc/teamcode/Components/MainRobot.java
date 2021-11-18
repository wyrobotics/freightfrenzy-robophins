package org.firstinspires.ftc.teamcode.Components;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Components.Hardware.Extender;
import org.firstinspires.ftc.teamcode.Components.Hardware.Intake;
import org.firstinspires.ftc.teamcode.Components.Hardware.Spinner;
import org.firstinspires.ftc.teamcode.Components.Software.OpenCVSampler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Components.Software.RealsenseLocalizer;

public class MainRobot extends SampleMecanumDrive {

    private Extender extender;
    private Intake intake;
    private Spinner spinner;
    private OpenCVSampler openCVSampler;

    public MainRobot(HardwareMap hardwareMap, Telemetry telemetry) {

        super(hardwareMap);
        setLocalizer(new RealsenseLocalizer(hardwareMap, telemetry));

        extender = new Extender(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);
        spinner = new Spinner(hardwareMap, telemetry);

        openCVSampler = new OpenCVSampler(hardwareMap, telemetry);

    }

}
