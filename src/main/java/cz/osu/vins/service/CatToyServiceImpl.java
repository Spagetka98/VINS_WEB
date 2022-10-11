package cz.osu.vins.service;

import cz.osu.vins.error.exception.InvalidTokenException;
import cz.osu.vins.models.helpers.VoiceActivation;
import cz.osu.vins.models.database.CatToy;
import cz.osu.vins.models.embedded.VoiceData;
import cz.osu.vins.repository.CatToyRepository;
import cz.osu.vins.response.ToyActivationInformationResponse;
import cz.osu.vins.response.ToyVoiceActivationInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatToyServiceImpl implements CatToyService {
    private final CatToyRepository catToyRepository;

    @Override
    public void checkToken(String token) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        if (!this.catToyRepository.existsByToken(token))
            throw new InvalidTokenException("This Token is not in our database !");
    }

    @Override
    public void toyActivated(String token) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        CatToy foundedToy = this.catToyRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("This Token is not in our database !"));

        foundedToy.getToyActivated().add(Instant.now());

        this.catToyRepository.save(foundedToy);
    }

    @Override
    public void toyTokenRegistration(String token) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        if (this.catToyRepository.existsByToken(token))
            throw new InvalidTokenException("This Token is in our database !");

        this.catToyRepository.save(new CatToy(token));
    }

    @Override
    public void toyVoiceActivation(String token, long voiceData) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        CatToy foundedToy = this.catToyRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("This Token is not in our database !"));

        foundedToy.getVoiceData().add(new VoiceData(voiceData, Instant.now()));

        this.catToyRepository.save(foundedToy);
    }

    @Override
    public ToyActivationInformationResponse getToyActivationInformation(String token) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        CatToy foundedToy = this.catToyRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("This Token is not in our database !"));

        List<String> numberOfActivationToday = foundedToy.getToyActivated()
                .stream().filter((activation ->
                        activation.isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.of("Europe/Prague")).toInstant()) &&
                                activation.isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.of("Europe/Prague")).toInstant())
                )).map(Instant::toString).toList();

        return new ToyActivationInformationResponse(
                numberOfActivationToday.size(),
                foundedToy.getToyActivated().size(),
                numberOfActivationToday
        );
    }

    @Override
    public ToyVoiceActivationInformationResponse getToyVoiceActivationInformation(String token) {
        if (token == null || token.isBlank())
            throw new InvalidTokenException("Parameter Token cannot be null or empty !");

        CatToy foundedToy = this.catToyRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("This Token is not in our database !"));

        List<VoiceActivation> numberOfVoiceWarnings = foundedToy.getVoiceData().stream()
                .filter((voiceData ->
                        voiceData.getTimeOfCreation().isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.of("Europe/Prague")).toInstant()) &&
                                voiceData.getTimeOfCreation().isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.of("Europe/Prague")).toInstant())
                )).map(voiceData -> new VoiceActivation(voiceData.getVoiceData(),voiceData.getTimeOfCreation().toString())).toList();

        return new ToyVoiceActivationInformationResponse(
                numberOfVoiceWarnings
        );
    }
}
