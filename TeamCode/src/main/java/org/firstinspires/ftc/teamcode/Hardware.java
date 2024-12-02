package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hardware extends DriveConstants{
    public DcMotor fl;

    public Hardware(HardwareMap hardwareMap) {
        fl = hardwareMap.get(DcMotor.class, "front");
    }
}
