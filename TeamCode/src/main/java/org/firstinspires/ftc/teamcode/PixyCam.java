package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

public class PixyCam
{
    private I2cDeviceSynch pixy;

    public PixyCam(I2cDeviceSynch pixy)
    {
        this.pixy = pixy;

        pixy.setReadWindow(new I2cDeviceSynch.ReadWindow (1, 26, I2cDeviceSynch.ReadMode.REPEAT));

        pixy.engage();
    }

    public double firstBlockAngle()
    {
        //TODO finish this thing
        return 0;
    }
}
