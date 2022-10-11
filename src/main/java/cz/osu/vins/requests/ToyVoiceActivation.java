package cz.osu.vins.requests;

import javax.validation.constraints.NotBlank;

public record ToyVoiceActivation(@NotBlank String token, long voiceData) {
}
