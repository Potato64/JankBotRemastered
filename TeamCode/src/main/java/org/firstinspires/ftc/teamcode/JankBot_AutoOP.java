
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="AutoOP", group="Linear Opmode")
public class JankBot_AutoOP extends LinearOpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private Arm arm;
    private DriveBase base;

    private I2cDeviceSynch pixy;

    @Override
    public void runOpMode()
    {

    }

}
