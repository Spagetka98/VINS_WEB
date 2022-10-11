package cz.osu.vins.requests;

import javax.validation.constraints.NotBlank;

public record ToyToken(@NotBlank String token) {
}
