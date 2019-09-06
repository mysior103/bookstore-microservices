package pl.podles.ratingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Rating {
    @Id
    @JsonIgnore
    private String _id;

    private int rate;

    private String isbn;

    private String username;
}
