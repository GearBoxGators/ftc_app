package com.gearboxgators.opmodes;

import com.gearboxgators.libraries.GatorsHardwareClass;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TeleOpTank extends OpMode{

    GatorsHardwareClass map = new GatorsHardwareClass(DcMotor.RunMode.RUN_WITHOUT_ENCODER, DcMotor.ZeroPowerBehavior.FLOAT);
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init()  {
        telemetry.addData("Init Status:","incomplete");
        map.init(hardwareMap);
        telemetry.addData("Init Status:","complete");
    }

    @Override
    public void init_loop() {


    }

    @Override
    public void start() {


    }

    @Override
    public void loop()  {
//        map.rightBackMotor.setPower(gamepad1.right_stick_y);
//        map.leftBackMotor.setPower(gamepad1.left_stick_y);
//        map.rightFrontMotor.setPower(gamepad1.right_stick_y);
//        map.leftFrontMotor.setPower(gamepad1.left_stick_y);
        double leftY = gamepad1.left_stick_y;
        double leftX = gamepad1.left_stick_x;
        double rightY = gamepad1.right_stick_y;
        double rightX = gamepad1.right_stick_x;
        //Front right = leftY-leftX-rightX Back right = leftY+leftX-rightX Front left = leftY+leftX+rightX Back left = leftY-leftX+rightX
        map.rightFrontMotor.setPower(leftY-leftX-rightX);
        map.rightBackMotor.setPower(leftY+leftX-rightX);
        map.leftFrontMotor.setPower(leftY+leftX+rightX);
        map.leftBackMotor.setPower(leftY-leftX+rightX);
    }

}
