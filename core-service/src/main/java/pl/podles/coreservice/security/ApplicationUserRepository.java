package pl.podles.coreservice.security;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.podles.coreservice.model.ApplicationUser;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser,String> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
