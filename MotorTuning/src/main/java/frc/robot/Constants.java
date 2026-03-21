package frc.robot;

public final class Constants {
    public enum ControlMode {
        VOLTAGE,
        TORQUE,
    };

    public final class Tuning {
        public static final int motorID = 21;
        public static final ControlMode controlMode = ControlMode.TORQUE;

        public static class Torque {
            public static class KS {
                public static double minCurrent = 3.0;
                public static double maxCurrent = 10.0;
                public static int trials = 1000;
                public static double trialSeconds = 0.1;
            }
            public static class KV {
                public static double minCurrent = 3.0;
                public static double maxCurrent = 5.0; // Stall current is 476A!
                public static int trials = 5;
                public static double trialSeconds = 30.0;
            }
        }
    }
    
}
