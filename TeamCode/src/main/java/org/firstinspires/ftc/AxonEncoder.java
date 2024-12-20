import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AxonEncoder
{
    private CRServo intakeServo;
    private double minPosition = 0.2;
    private double maxPosition = 0.8;
    //get our analog input from the hardwareMap
    AnalogInput analogInput = hardwareMap.get(AnalogInput.class, "myanaloginput");

    // get the voltage of our analog line
// divide by 3.3 (the max voltage) to get a value between 0 and 1
// multiply by 360 to convert it to 0 to 360 degrees
    double position = analogInput.getVoltage() / 3.3 * 360;
    public void init(HardwareMap hardwareMap)
    {
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");
    }

    public void moveIntake(double position)
    {
        if (position < minPosition)
        {
            position = minPosition;
        } else if (position > maxPosition)
        {
            position = maxPosition;
        }
        intakeServo.setPower(position);
    }
}