package frc.Subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AddressableLEDBufferView;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SimpleLEDSubsystem  extends SubsystemBase {
    private AddressableLED ledString;
    private AddressableLEDBuffer buffer;

    public SimpleLEDSubsystem(int pwmPin, int length) {
        ledString = new AddressableLED(pwmPin);
        buffer = new AddressableLEDBuffer(length);

        ledString.setLength(length);
        ledString.setData(buffer);
        ledString.start();
    }

    public AddressableLEDBuffer buffer() {
        return buffer;
    }

    public AddressableLEDBufferView viewWithRange(int startIndex, int endIndex) {
        return new AddressableLEDBufferView(buffer, startIndex, endIndex);
    }

    public void periodic() {
        ledString.setData(buffer);
    }
    
}
