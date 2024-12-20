while (opModeIsActive()) {
        if (gamepad1.y){
        //move to position 0
        test_servo.setPosition(0);
    
            } else if (gamepad1.x || gamepad1.b) {
        //move to position 0.5
        test_servo.setPosition(0.5);

            } else if (gamepad1.a) {
        //move to position 1
        test_servo.setPosition(1);
            
            telemetry.addData("Servo Position", test_servo.getPosition());
        telemetry.addData("Status", "Running");
            telemetry.update();

        }