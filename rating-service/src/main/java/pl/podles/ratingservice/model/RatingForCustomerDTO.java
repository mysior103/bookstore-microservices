package pl.podles.ratingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingForCustomerDTO {
    private String title;
    private String isbn;
    private int rate;
}
