package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Decode")
public class Decode extends LinearOpMode {
    private DcMotor leftFront, rightFront, leftBack, rightBack, topShoot, bottomShoot;
    private CRServo frontServo, middleServo, backServo;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        frontServo = hardwareMap.get(CRServo.class, "frontServo");
        middleServo = hardwareMap.get(CRServo.class, "middleServo");
        backServo = hardwareMap.get(CRServo.class, "backServo");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        topShoot = hardwareMap.get(DcMotor.class, "topShoot");
        bottomShoot = hardwareMap.get(DcMotor.class, "bottomShoot");
        bottomShoot.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while(opModeIsActive()) {
                double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
                double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                double rx = gamepad1.right_stick_x;

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x + rx) / denominator;
                double frontRightPower = (y - x - rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                leftFront.setPower(frontLeftPower);
                leftBack.setPower(backLeftPower);
                rightFront.setPower(frontRightPower);
                rightBack.setPower(backRightPower);

                if (gamepad2.left_trigger > 0.1) {
                    frontServo.setPower(1);
                    middleServo.setPower(1);
                    backServo.setPower(1);
                    topShoot.setPower(1);
                    bottomShoot.setPower(1);
                }
                else {
                    frontServo.setPower(0);
                    middleServo.setPower(0);
                    backServo.setPower(0);
                    topShoot.setPower(0);
                    bottomShoot.setPower(0);
                }
                telemetry.update();
            }
        }
    }
}