package cz.osu.vins.service;

import cz.osu.vins.response.ToyActivationInformationResponse;
import cz.osu.vins.response.ToyVoiceActivationInformationResponse;

public interface CatToyService {

    void checkToken(String token);

    void toyActivated(String token);

    void toyTokenRegistration(String token);

    void toyVoiceActivation(String token, long voiceData);

    ToyActivationInformationResponse getToyActivationInformation(String token);

    ToyVoiceActivationInformationResponse getToyVoiceActivationInformation(String token);
}
