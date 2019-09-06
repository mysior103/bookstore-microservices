package pl.podles.ratingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.podles.ratingservice.RatingExistException;
import pl.podles.ratingservice.model.*;
import pl.podles.ratingservice.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RatingRepository ratingRepository;

    @Value("${url.book-service}")
    private String bookUrl;

    @Value("${url.customer-service}")
    private String customerUrl;

    @Override
    public RatingForBookDTO getBookRatingAverage(String isbn) {
        List<Rating> allByIsbn = ratingRepository.findAllByIsbn(isbn);
        int numberOfRates = 0;
        int average = 0;
        if (!allByIsbn.isEmpty()) {
            numberOfRates = allByIsbn.size();
            int sum = allByIsbn.stream().map(Rating::getRate).reduce(0, Integer::sum);
            average = sum / numberOfRates;
        }
        return new RatingForBookDTO(isbn, average, numberOfRates);
    }

    @Override
    public void saveRating(String isbn, RatingWithUsernameDTO ratingWithUsernameDTO) throws RatingExistException {
        if (restTemplate.exchange(bookUrl + "/" + isbn, HttpMethod.GET, null, String.class).getStatusCode().is2xxSuccessful()
                && restTemplate.exchange(customerUrl + "/" + ratingWithUsernameDTO.getUsername(), HttpMethod.GET, null, String.class).getStatusCode().is2xxSuccessful()) {
            if (ratingRepository.findByIsbnAndUsername(isbn, ratingWithUsernameDTO.getUsername()).isPresent()) {
                throw new RatingExistException("Rating for " + ratingWithUsernameDTO.getUsername() + " and book " + isbn + " already exist.");
            }
            ratingRepository.save(new Rating(null, ratingWithUsernameDTO.getRate(), isbn, ratingWithUsernameDTO.getUsername()));
        }
    }

    @Override
    public List<RatingForCustomerDTO> getUserRating(String username) {
        List<RatingForCustomerDTO> forCustomer = new ArrayList<>();
        for (Rating r : ratingRepository.findByUsername(username)) {
            Book book = restTemplate.getForObject(bookUrl + "/" + r.getIsbn(), Book.class);
            forCustomer.add(new RatingForCustomerDTO(book.getTitle(), book.getIsbn(), r.getRate()));
        }
        return forCustomer;
    }
}
