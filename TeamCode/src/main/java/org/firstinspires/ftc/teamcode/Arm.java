package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    private final double liftLowerLimit = 0;
    private final double liftUpperLimit = 500;

    private DcMotor lift;
    private DcMotor extend;

    private Hopper hopper;
    private Latch latch;

    public Arm (DcMotor lift, DcMotor extend,
                Servo leftHopper, Servo rightHopper, Servo tiltHopper,
                Servo latchRelease)
    {
        this.lift = lift;
        this.extend = extend;

        hopper = new Hopper(leftHopper, rightHopper, tiltHopper);
        latch = new Latch(latchRelease);
    }

    public void setMode(DcMotor.RunMode mode)
    {
        lift.setMode(mode);
    }

    public void setPosition(int position)
    {
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setTargetPosition(position);
    }

    public void setLiftPower(double power)
    {
        lift.setPower(power);
    }

    public void set
}
