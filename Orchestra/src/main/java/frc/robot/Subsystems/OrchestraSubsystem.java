package frc.robot.Subsystems;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.configs.AudioConfigs;
import com.ctre.phoenix6.hardware.TalonFX;

public class OrchestraSubsystem {
    private Orchestra orchestra;

    public OrchestraSubsystem(TalonFX... motors) {
        orchestra = new Orchestra();
        for (TalonFX motor: motors) {
            orchestra.addInstrument(motor);
            AudioConfigs audioConfig = new AudioConfigs().withAllowMusicDurDisable(true);
            motor.getConfigurator().apply(audioConfig);
        }
    }

    public void queueMusicFile(String musicFile) {
        orchestra.loadMusic(musicFile);
    }

    public void play() {
        orchestra.play();
    }

    public void stop() {
        orchestra.stop();
    }
}
