package pl.podles.bookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.podles.bookservice.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
