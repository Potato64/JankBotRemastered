package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

public class Latch {

    private final double openLimit = 0;
    private final double closedLimit = 0.5;

    private final int unwoundTicks = 5000;

    private Servo releaseServo;

    private DcMotor winch;

    public Latch(Servo releaseServo, DcMotor winch)
    {
        this.releaseServo = releaseServo;
        this.releaseServo.scaleRange(openLimit, closedLimit);

        this.winch = winch;
        winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void release()
    {
        winch.setTargetPosition(unwoundTicks);
        winch.setPower(0.5);

        try
        {
            sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if (!winch.isBusy())
        {
            releaseServo.setPosition(0);
        }
    }

    public void unrelease()
    {
        releaseServo.setPosition(1);
    }

    public void climb()
    {
        winch.setTargetPosition(0);
    }
}
