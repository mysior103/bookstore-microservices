package pl.podles.coreservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.podles.coreservice.exception.NoAccessException;
import pl.podles.coreservice.model.ApplicationUser;
import pl.podles.coreservice.model.UserRoleEnum;
import pl.podles.coreservice.security.ApplicationUserRepository;

import java.util.Optional;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {



    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public String getLoggedUsername() {
        return getAuthentication().getName();
    }

    @Override
    public boolean checkIfUserExist(String username) {
        return applicationUserRepository.findApplicationUserByUsername(username).isPresent();
    }

    @Override
    public void saveNewUser(ApplicationUser applicationUser) {
        applicationUserRepository.save(applicationUser);
    }

    @Override
    public UserRoleEnum getLoggedRole() {
        return applicationUserRepository
                .findApplicationUserByUsername(getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user")).getUserRole();
    }


    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();

    }

    @Override
    public void checkAccess(UserRoleEnum role) throws NoAccessException {
        if (!getLoggedRole().equals(role)) {
            throw new NoAccessException();
        }
    }

    @Override
    public Optional<ApplicationUser> getUser(String username) {
        return applicationUserRepository.findApplicationUserByUsername(username);
    }
}
