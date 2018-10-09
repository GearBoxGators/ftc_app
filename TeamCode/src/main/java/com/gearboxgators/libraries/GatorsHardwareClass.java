package com.gearboxgators.libraries;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class GatorsHardwareClass {

    //---Motors
    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightBackMotor = null;

    //---Sensors
    public TouchSensor leftTouch = null;
/*
    ColorSensor 1SensorColor = null;
    DistanceSensor fSensorDistance = null;

    ColorSensor bSensorColor = null;
    DistanceSensor bSensorDistance = null;
*/
    HardwareMap map = null;

    private DcMotor.RunMode initialMode = null;

    //---map constructor
        public GatorsHardwareClass(DcMotor.RunMode mode) {
            initialMode = mode;
        }

    //---Robot init
        public void init(HardwareMap mapA)  {
            map = mapA;

            leftTouch = map.get(TouchSensor.class, "sensor_touch_right");

/*
            fSensorColor = map.get(ColorSensor.class, "sensor_coldis_front");
            fSensorDistance = map.get(DistanceSensor.class, "sensor_coldis_front");

            bSensorColor = map.get(ColorSensor.class, "sensor_coldis_bottom");
            bSensorDistance = map.get(DistanceSensor.class, "sensor_coldis_bottom");

            leftFrontMotor = map.dcMotor.get("left_front");
            rightFrontMotor = map.dcMotor.get("right_front");
            leftBackMotor = map.dcMotor.get("left_back");
            rightBackMotor = map.dcMotor.get("right_back");

            leftFrontMotor.setMode(initialMode);
            rightFrontMotor.setMode(initialMode);
            leftBackMotor.setMode(initialMode);
            rightBackMotor.setMode(initialMode);

            leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBackMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);



            resetDriveTrain();
            stopDrive();*/
        }

    //integrated methods for OpModes
        public void resetDriveTrain()   {
            leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void stopDrive()  {
            leftBackMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftBackMotor.setPower(0);
            rightBackMotor.setPower(0);
        }

        public void setZeroPower(String type)  {
            if(type.equals("Coast")||type.equals("coast")||type.equals("COAST")
                    ||type.equals("Float")||type.equals("float")||type.equals("FLOAT")) {
                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }
            else if(type.equals("Brake")||type.equals("brake")||type.equals("BRAKE"))   {
                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            else    {
                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);

            }
        }
}