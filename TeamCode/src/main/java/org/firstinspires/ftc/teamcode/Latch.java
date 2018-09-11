package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Latch {

    private final double openLimit = 0;
    private final double closedLimit = 0.5;

    private Servo releaseServo;

    public Latch(Servo releaseServo)
    {
        this.releaseServo = releaseServo;
        this.releaseServo.scaleRange(openLimit, closedLimit);
    }

    public void release()
    {
        releaseServo.setPosition(0);
    }

    public void unrelease()
    {
        releaseServo.setPosition(1);
    }
}
