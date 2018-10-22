package com.gearboxgators.libraries;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import java.util.ArrayList;

public class GatorsHardwareClass {
    //---Motors
    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftBackMotor = null;
    public DcMotor rightBackMotor = null;
    private ArrayList<DcMotor> motors = new ArrayList<DcMotor>();

    //---Sensors
    private TouchSensor touchLeft = null;
    private TouchSensor touchRight = null;

    private ColorSensor ColorRight = null;
    private DistanceSensor CDistanceRight = null;

    private ColorSensor ColorLeft = null;
    private DistanceSensor CDistanceLeft = null;

    HardwareMap map = null;

    private DcMotor.RunMode initialMode = null;

    //---map constructor
        public GatorsHardwareClass(DcMotor.RunMode mode, DcMotor.ZeroPowerBehavior powerBehaviour) {
            this.setZeroPower(powerBehaviour);
            initialMode = mode;

        }

    //---Robot init
        public void init(HardwareMap mapA)  {
            map = mapA;
            //---Motors---
                //Drive Train Motors
                leftBackMotor = map.get(DcMotor.class, "left_back_drive");
                leftFrontMotor = map.get(DcMotor.class, "left_front_drive");
                rightBackMotor = map.get(DcMotor.class, "right_back_drive");
                rightFrontMotor = map.get(DcMotor.class, "right_front_drive");

                //reverse the right wheels
                rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                rightBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

                motors.add(leftBackMotor);
                motors.add(leftFrontMotor);
                motors.add(rightBackMotor);
                motors.add(rightFrontMotor);
            //---Sensors---
                //Touch Sensors
                touchLeft = map.get(TouchSensor.class, "sensor_touch_left");
                touchRight = map.get(TouchSensor.class, "sensor_touch_right");
                //Bottom Right Color Distance
                ColorRight = map.get(ColorSensor.class, "sensor_color_distance_right");
                CDistanceRight = map.get(DistanceSensor.class, "sensor_color_distance_right");
                //Bottom Left Color Distance
                ColorLeft = map.get(ColorSensor.class, "sensor_color_distance_left");
                CDistanceLeft = map.get(DistanceSensor.class, "sensor_color_distance_left");

            resetDriveTrain();
            stopDrive();
        }

    //---Integrated Methods For OpModes---
        public void resetDriveTrain()   {
            for (int i = 0; i < motors.size(); i++)
                motors.get(i).setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public void stopDrive()  {
            for (int i = 0; i < motors.size(); i++)
                motors.get(i).setPower(0);
        }

        public void setZeroPower(DcMotor.ZeroPowerBehavior behavior)  {
            //a more concise way. however, when this method is called the zero power behavior must be passed as the int
            for (int i = 0; i < motors.size(); i++)
                motors.get(i).setZeroPowerBehavior(behavior);
//            if(type.equals("Coast")||type.equals("coast")||type.equals("COAST")
//                    ||type.equals("Float")||type.equals("float")||type.equals("FLOAT")) {
//                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//            }
//            else if(type.equals("Brake")||type.equals("brake")||type.equals("BRAKE"))   {
//                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            }
//            else    {
//                leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
//                rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
//                leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
//                rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.UNKNOWN);
//
//            }
        }
}