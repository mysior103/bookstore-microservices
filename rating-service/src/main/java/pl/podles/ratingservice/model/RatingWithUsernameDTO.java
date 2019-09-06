package pl.podles.ratingservice.model;

import lombok.Data;

@Data
public class RatingWithUsernameDTO {

    private int rate;
    private String username;
}
