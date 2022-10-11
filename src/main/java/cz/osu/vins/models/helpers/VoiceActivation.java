package cz.osu.vins.models.helpers;

import lombok.Data;

@Data
public class VoiceActivation {
    private long voiceData;
    private String time;

    public VoiceActivation(long voiceData, String timeOfCreation) {
        this.voiceData=voiceData;
        this.time = timeOfCreation;
    }
}
