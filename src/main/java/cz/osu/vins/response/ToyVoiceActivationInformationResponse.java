package cz.osu.vins.response;

import cz.osu.vins.models.helpers.VoiceActivation;

import java.util.List;

public record ToyVoiceActivationInformationResponse (List<VoiceActivation> data){
}
