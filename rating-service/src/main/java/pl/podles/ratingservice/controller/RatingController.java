package pl.podles.ratingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.podles.ratingservice.RatingExistException;
import pl.podles.ratingservice.model.RatingWithUsernameDTO;
import pl.podles.ratingservice.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/book/{isbn}")
    ResponseEntity getRatingForBook(@PathVariable String isbn) {
        return ResponseEntity.ok(ratingService.getBookRatingAverage(isbn));
    }

    @PostMapping("/book/{isbn}")
    ResponseEntity addRating(@PathVariable String isbn, @RequestBody RatingWithUsernameDTO ratingWithUsernameDTO) {
        try {
            ratingService.saveRating(isbn, ratingWithUsernameDTO);
            return ResponseEntity.ok().build();
        } catch (RatingExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/user/{username}")
    ResponseEntity getRatingForUser(@PathVariable String username) {
        return ResponseEntity.ok(ratingService.getUserRating(username));

    }
}
