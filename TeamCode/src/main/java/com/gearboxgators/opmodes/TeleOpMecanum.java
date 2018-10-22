package com.gearboxgators.opmodes;

import com.gearboxgators.libraries.GatorsHardwareClass;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp:Mecanum_Capable")
public class TeleOpMecanum extends OpMode {
    GatorsHardwareClass map = new GatorsHardwareClass(DcMotor.RunMode.RUN_WITHOUT_ENCODER, DcMotor.ZeroPowerBehavior.FLOAT);
    private ElapsedTime runtime = new ElapsedTime();

    double leftX = gamepad1.left_stick_x;
    double leftY = gamepad1.left_stick_y;
    double rightX = gamepad1.right_stick_x;
    double rightY = gamepad1.right_stick_y;

    @Override
    public void init()  {
        String initStatus = "incomplete";
        telemetry.addData("---Init Status---:",initStatus);
        map.init(hardwareMap);
        initStatus = "complete";
        telemetry.clearAll();
        telemetry.addData("---Init Status---:",initStatus);
    }

    @Override
    public void init_loop() {

    }
    @Override
    public void start() {


    }

    @Override
    public void loop()  {
        leftX = gamepad1.left_stick_x;
        leftY = gamepad1.left_stick_y;
        rightX = gamepad1.right_stick_x;
        rightY = gamepad1.right_stick_y;
        
        map.rightBackMotor.setPower(leftY+leftX-rightX);
        map.leftBackMotor.setPower(leftY-leftX+rightX);
        map.rightFrontMotor.setPower(leftY-leftX-rightX);
        map.leftFrontMotor.setPower(leftY+leftX+rightX);
    }
}
