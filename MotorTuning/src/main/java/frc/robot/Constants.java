package frc.robot;

public final class Constants {
    public final class Tuning {
        public static final int motorID = 32;

        public static class Torque {
            public static class KS {
                public static double maxStaticCurrent = 10.0;
                public static int trials = 1000;
                public static double trialSeconds = 0.1;
            }
            public static class KV {
                public static double minCurrent = 0.0;
                public static double maxCurrent = 200; // Stall current is 476A!
                public static int trials = 10;
                public static double trialSeconds = 0.5;
            }
        }
    }
    
}
