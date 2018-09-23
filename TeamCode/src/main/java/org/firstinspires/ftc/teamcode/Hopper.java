package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Hopper
{
    //TODO find actual limits
    private final double OPEN_LIMIT = 0;
    private final double CLOSED_LIMIT = 1;
    private final double TILT_LOWER_LIMIT = 0;
    private final double TILT_UPPER_LIMIT = 1;

    private Servo left;
    private Servo right;
    private Servo tilt;

    public Hopper(Servo left, Servo right, Servo tilt)
    {
        this.left = left;
        this.right = right;
        this.tilt = tilt;

        this.left.scaleRange(OPEN_LIMIT, CLOSED_LIMIT);
        this.right.scaleRange(OPEN_LIMIT, CLOSED_LIMIT);
        this.tilt.scaleRange(TILT_LOWER_LIMIT, TILT_UPPER_LIMIT);

        this.right.setDirection(Servo.Direction.REVERSE);
    }

    public void open()
    {
        left.setPosition(0);
        right.setPosition(0);
    }

    public void close()
    {
        left.setPosition(1);
        right.setPosition(1);
    }

    public boolean isClosed()
    {
        return left.getPosition() > 0.5;
    }

    public void dump()
    {
        tilt.setPosition(0);
    }

    public void undump()
    {
        tilt.setPosition(1);
    }
}
