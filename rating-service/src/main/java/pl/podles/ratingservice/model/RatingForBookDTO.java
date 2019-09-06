package pl.podles.ratingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingForBookDTO {

    String bookIsbn;
    int averageRate;
    int numberOfRates;
}
