package cz.osu.vins.controller;


import cz.osu.vins.requests.ToyToken;
import cz.osu.vins.requests.ToyVoiceActivation;
import cz.osu.vins.response.ToyActivationInformationResponse;
import cz.osu.vins.response.ToyVoiceActivationInformationResponse;
import cz.osu.vins.service.CatToyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arduinoToy")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ToyController {
    private final CatToyService catToyService;

    @GetMapping("/getToyActivationInformation")
    public ResponseEntity<?> getToyActivationInformation(@RequestParam String token) {

        ToyActivationInformationResponse toyActivationInformationResponse = this.catToyService.getToyActivationInformation(token);

        return ResponseEntity.ok(toyActivationInformationResponse);
    }

    @GetMapping("/getToyVoiceActivationInformation")
    public ResponseEntity<?> getToyVoiceActivationInformation(@RequestParam String token) {

        ToyVoiceActivationInformationResponse toyVoiceActivationInformationResponse = this.catToyService.getToyVoiceActivationInformation(token);

        return ResponseEntity.ok(toyVoiceActivationInformationResponse);
    }

    @PostMapping("/checkToken")
    public ResponseEntity<?> checkToken(@RequestBody ToyToken toyTokenRequest) {

        this.catToyService.checkToken(toyTokenRequest.token());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/toyTokenRegistration")
    public ResponseEntity<?> toyTokenRegistration(@RequestBody ToyToken toyTokenRequest) {

        this.catToyService.toyTokenRegistration(toyTokenRequest.token());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/toyActivated")
    public ResponseEntity<?> toyActivated(@RequestBody ToyToken toyTokenRequest) {

        this.catToyService.toyActivated(toyTokenRequest.token());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/toyVoiceActivated")
    public ResponseEntity<?> toyVoiceActivated(@RequestBody ToyVoiceActivation toyVoiceActivationRequest) {

        this.catToyService.toyVoiceActivation(toyVoiceActivationRequest.token(),toyVoiceActivationRequest.voiceData());

        return ResponseEntity.ok().build();
    }

}
