package cz.osu.vins.models.embedded;

import lombok.Data;

import javax.validation.constraints.Min;
import java.time.Instant;

@Data
public class VoiceData {
    @Min(0)
    private long voiceData;

    private Instant timeOfCreation;

    public VoiceData(long voiceData, Instant timeOfCreation) {
        this.voiceData = voiceData;
        this.timeOfCreation = timeOfCreation;
    }
}
