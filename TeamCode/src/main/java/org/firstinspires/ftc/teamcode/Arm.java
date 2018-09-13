package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

//    private final double liftLowerLimit = 0;
//    private final double liftUpperLimit = 500;
//
//    private final double extendLowerLimit = 0;
//    private final double extendUpperLimit = 1000;

    private DcMotor lift;
    private DcMotor extend;

    private DigitalChannel liftLimitSwitch;
    private DigitalChannel extendLimitSwitch;

    private Hopper hopper;
    private Latch latch;

    public Arm (DcMotor lift, DcMotor extend, DigitalChannel liftLimitSwitch, DigitalChannel extendLimitSwitch,
                Servo leftHopper, Servo rightHopper, Servo tiltHopper,
                Servo latchRelease, DcMotor winch)
    {
        this.lift = lift;
        this.extend = extend;

        hopper = new Hopper(leftHopper, rightHopper, tiltHopper);
        latch = new Latch(latchRelease, winch);
    }

    public void setLiftMode(DcMotor.RunMode mode)
    {
        lift.setMode(mode);
    }

    public void setLiftPower(double power)
    {
        lift.setPower(power);
    }

    public void setLiftPosition(int position)
    {
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setTargetPosition(position);
    }

    public void setExtendMode(DcMotor.RunMode mode)
    {
        extend.setMode(mode);
    }

    public void setExtendPower(double power)
    {
        extend.setPower(power);
    }

    public void setExtendPostion(int postion)
    {
        extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extend.setTargetPosition(postion);
    }
}
