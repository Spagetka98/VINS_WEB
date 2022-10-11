package cz.osu.vins.response;

import java.util.List;

public record ToyActivationInformationResponse(int todayActivation, int totalActivation, List<String> todayActivationTimes) {
}
