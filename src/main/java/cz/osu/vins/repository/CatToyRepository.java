package cz.osu.vins.repository;

import cz.osu.vins.models.database.CatToy;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CatToyRepository extends MongoRepository<CatToy, String> {

    boolean existsByToken(String token);

    Optional<CatToy> findByToken(String token);
}
