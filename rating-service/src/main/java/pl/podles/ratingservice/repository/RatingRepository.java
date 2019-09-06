package pl.podles.ratingservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.podles.ratingservice.model.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findAllByIsbn(String isbn);

    Optional<Rating> findByIsbnAndUsername(String isbn, String username);

    List<Rating> findByUsername(String username);

}
