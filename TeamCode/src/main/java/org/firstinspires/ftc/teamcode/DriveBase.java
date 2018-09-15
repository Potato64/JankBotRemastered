package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class DriveBase {

    private final double KP = 0.02;
//    private final double KI = 0.001;
//
//    private double accum = 0;

    private DcMotor left1;
    private DcMotor left2;
    private DcMotor right1;
    private DcMotor right2;

    private BNO055IMU imu;

    private double speed;
    private double targetHeading;
    private double rotSpeed;

    public DriveBase(DcMotor left1, DcMotor left2,
                DcMotor right1, DcMotor right2,
                BNO055IMU imu)
    {
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;

        right1.setDirection(DcMotorSimple.Direction.REVERSE);
        right2.setDirection(DcMotorSimple.Direction.REVERSE);

        this.imu = imu;
    }

    public double getHeading()
    {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public void setMode(DcMotor.RunMode mode)
    {
        left1.setMode(mode);
        left2.setMode(mode);
        right1.setMode(mode);
        right2.setMode(mode);
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public void setRotSpeed(double speed)
    {
        if (this.rotSpeed > 0 && speed == 0)
        {
            targetHeading = getHeading();
        }

        this.rotSpeed = speed;
    }

    public void setTargetHeading(double heading)
    {
        this.targetHeading = heading;
    }

    public void update()
    {
        double leftPower;
        double rightPower;


        if (rotSpeed != 0)
        {
            leftPower = speed + rotSpeed;
            rightPower = speed - rotSpeed;

            double maxPower = Math.abs(speed) + Math.abs(rotSpeed);
            if (maxPower > 1) {
                leftPower /= maxPower;
                rightPower /= maxPower;
            }
        }
        else
        {
            double error = getHeading() - targetHeading;
            double powerChange = error * KP;// + error * KI;

            leftPower = speed + powerChange;
            rightPower = speed - powerChange;

            double maxPower = Math.abs(speed) + Math.abs(powerChange);
            if (maxPower > 1) {
                leftPower /= maxPower;
                rightPower /= maxPower;
            }
        }

        left1.setPower(leftPower);
        left2.setPower(leftPower);
        right1.setPower(rightPower);
        right2.setPower(rightPower);
    }
}