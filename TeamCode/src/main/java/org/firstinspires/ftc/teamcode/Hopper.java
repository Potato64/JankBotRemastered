package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Hopper {

    private final double leftOpenLimit = 0;
    private final double leftClosedLimit = 1;
    private final double rightOpenLimit = 0;
    private final double rightClosedLimit = 1;
    private final double tiltLowerLimit = 0;
    private final double tiltupperLimit = 1;

    private Servo left;
    private Servo right;
    private Servo tilt;

    public Hopper(Servo left, Servo right, Servo tilt)
    {
        this.left = left;
        this.right = right;
        this.tilt = tilt;

        this.left.scaleRange(leftOpenLimit, leftClosedLimit);
        this.right.scaleRange(rightOpenLimit, rightClosedLimit);
        this.tilt.scaleRange(tiltLowerLimit, tiltupperLimit);

        this.right.setDirection(Servo.Direction.REVERSE);
    }

    public void open()
    {
        left.setPosition(0);
        right.setPosition(0);
    }

    public void closed()
    {
        left.setPosition(1);
        right.setPosition(1);
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
