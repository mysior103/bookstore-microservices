package pl.podles.coreservice.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.podles.coreservice.model.ApplicationUser;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    private ApplicationUserRepository applicationUserRepository;

    public UserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByUsername(username);
        if(!applicationUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.get().getUsername(),applicationUser.get().getPassword(), Collections.emptyList());
    }
}
