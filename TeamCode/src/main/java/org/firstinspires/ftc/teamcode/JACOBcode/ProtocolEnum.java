package org.firstinspires.ftc.teamcode.JACOBcode;

public enum ProtocolEnum {
    I2C(false),
    SERIALPORT(true);
    public final boolean value;
    ProtocolEnum(boolean value){
        this.value = value;
    }
}
