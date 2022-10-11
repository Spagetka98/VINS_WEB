package cz.osu.vins.response;

import org.springframework.http.HttpStatus;

public record MessageResponse(HttpStatus badRequest, String message, String time) {

}
