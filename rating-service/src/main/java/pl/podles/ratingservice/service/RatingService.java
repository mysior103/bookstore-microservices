package pl.podles.ratingservice.service;

import pl.podles.ratingservice.RatingExistException;
import pl.podles.ratingservice.model.RatingForBookDTO;
import pl.podles.ratingservice.model.RatingForCustomerDTO;
import pl.podles.ratingservice.model.RatingWithUsernameDTO;

import java.util.List;

public interface RatingService {
    RatingForBookDTO getBookRatingAverage(String isbn);

    void saveRating(String isbn, RatingWithUsernameDTO ratingWithUsernameDTO) throws RatingExistException;

    List<RatingForCustomerDTO> getUserRating(String username);
}
