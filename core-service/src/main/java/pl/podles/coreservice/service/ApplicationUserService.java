package pl.podles.coreservice.service;


import pl.podles.coreservice.exception.NoAccessException;
import pl.podles.coreservice.model.ApplicationUser;
import pl.podles.coreservice.model.UserRoleEnum;

import java.util.Optional;

public interface ApplicationUserService {
    String getLoggedUsername();

    boolean checkIfUserExist(String username);

    void saveNewUser(ApplicationUser applicationUser);

    UserRoleEnum getLoggedRole();

    void checkAccess(UserRoleEnum role) throws  NoAccessException;

    Optional<ApplicationUser> getUser(String username);
}
