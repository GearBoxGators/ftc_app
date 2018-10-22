package com.gearboxgators.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

/*
 * This is an example LinearOpMode that shows how to use
 * the REV Robotics Color-Distance Sensor.
 *
 * It assumes the sensor is configured with the name "sensor_color_distance".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 */
@TeleOp(name = "SensorTesting")
                            // Comment this out to add to the opmode list
public class SensorTestClass extends LinearOpMode {


    //test comment

    ColorSensor sensorColor1;
    DistanceSensor sensorDistance1;

    ColorSensor sensorColor2;
    DistanceSensor sensorDistance2;

    TouchSensor sensorTouch1;

    TouchSensor sensorTouch2;

    DcMotor rightFrontDrive;
    DcMotor leftFrontDrive;
    DcMotor rightBackDrive;
    DcMotor leftBackDrive;
    public void method(ColorSensor c1, DistanceSensor c2, String name)   {

        telemetry.addData(name + " Distance (cm)",
                String.format(Locale.US, "%.02f", c2.getDistance(DistanceUnit.CM))+" centimentiheckinmeters my dude!");

        telemetry.addData(name + " red", c1.red());
        telemetry.addData(name + " blue", c1.blue());
        telemetry.addData(name + " green", c1.green());

    }
    @Override
    public void runOpMode() {

        // get a reference to the color sensor.
        sensorColor1 = hardwareMap.get(ColorSensor.class, "sensor_color_distance1");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance1 = hardwareMap.get(DistanceSensor.class, "sensor_color_distance1");

        sensorColor2 = hardwareMap.get(ColorSensor.class, "sensor_color_distance2");

        // get a reference to the distance sensor that shares the same name.
        sensorDistance2 = hardwareMap.get(DistanceSensor.class, "sensor_color_distance2");

        sensorTouch1 = hardwareMap.get(TouchSensor.class, "sensor_touch1");
        sensorTouch2 = hardwareMap.get(TouchSensor.class, "sensor_touch2");

        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        rightFrontDrive =  hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        // wait for the start button to be pressed.
        waitForStart();

        // loop and read the RGB and distance data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.

        while (opModeIsActive()) {



            // send the info back to driver station using telemetry function.
            method(sensorColor1, sensorDistance1, "color sensor 1");
            method(sensorColor2, sensorDistance2, "color sensor 2");

            telemetry.addData("touch1",sensorTouch1.isPressed());
            telemetry.addData("touch2",sensorTouch2.isPressed());
            rightBackDrive.setPower(gamepad1.right_stick_y);
            rightFrontDrive.setPower(gamepad1.right_stick_y);
            leftBackDrive.setPower(gamepad1.left_stick_y);
            leftFrontDrive.setPower(gamepad1.left_stick_y);

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();
        }

        // Set the panel back to the default color
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.WHITE);
            }
        });
    }
}
