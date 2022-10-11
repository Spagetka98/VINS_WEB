package cz.osu.vins.models.database;

import cz.osu.vins.models.embedded.VoiceData;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "CatToy")
@Data
public class CatToy {
    @Id
    private String id;

    @Indexed(name = "token_index", unique = true)
    @NotBlank(message = "Token")
    private String token;

    private List<Instant> toyActivated;

    private List<VoiceData> voiceData;

    public CatToy(String token){
        this.token = token;
        this.toyActivated = new ArrayList<>();
        this.voiceData = new ArrayList<>();
    }
}
