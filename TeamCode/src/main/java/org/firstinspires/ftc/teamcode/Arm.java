package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    private final double liftLowerLimit = 0;
    private final double liftUpperLimit = 500;

    private final double extendLowerLimit = 0;
    private final double extendUpperLimit = 1000;

    private boolean isZeroed = false;

    private DcMotor lift;
    private DcMotor extend;

    private DigitalChannel liftLimitSwitch;
    private DigitalChannel extendLimitSwitch;

    public Hopper hopper;
    public Latch latch;

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
        int currentPosition = lift.getCurrentPosition();
        if (power >= 0 && currentPosition <= liftUpperLimit ||
                power <= 0 && currentPosition >= liftLowerLimit &&
                isZeroed)
        {
            lift.setPower(power);
        }
    }

    public void setLiftPosition(int position)
    {
        if (position >= liftLowerLimit && position <= liftUpperLimit && isZeroed)
        {
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setTargetPosition(position);
        }
    }

    public void setExtendMode(DcMotor.RunMode mode)
    {
        extend.setMode(mode);
    }

    public void setExtendPower(double power)
    {
        int currentPosition = extend.getCurrentPosition();
        if (power >= 0 && currentPosition <= extendUpperLimit ||
                power <= 0 && currentPosition >= extendLowerLimit &&
                isZeroed)
        {
            extend.setPower(power);
        }
    }

    public void setExtendPostion(int position)
    {
        if (position >= extendLowerLimit && position <= extendUpperLimit && isZeroed)
        {
            extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            extend.setTargetPosition(position);
        }
    }

    public void descend()
    {
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        latch.release();

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void zeroEncoders()
    {
        long startTime = System.currentTimeMillis();

        boolean liftLimit;
        boolean extendLimit;

        do
        {
            liftLimit = liftLimitSwitch.getState();
            extendLimit = extendLimitSwitch.getState();

            if (liftLimit)
            {
                lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            else
            {
                lift.setPower(-0.5);
            }

            if (extendLimit)
            {
                extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            else
            {
                extend.setPower(-0.5);
            }

            if (System.currentTimeMillis() - startTime >= 4500)
            {
                break;
            }
        } while (!liftLimit || !extendLimit);

        if (liftLimit && extendLimit)
        {
            isZeroed = true;
        }
    }
}
