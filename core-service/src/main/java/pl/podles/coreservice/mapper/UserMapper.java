package pl.podles.coreservice.mapper;

import pl.podles.coreservice.dto.UserDTO;
import pl.podles.coreservice.model.ApplicationUser;
import pl.podles.coreservice.model.UserRoleEnum;

public class UserMapper {
    public static ApplicationUser toEntity(UserDTO userDTO) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(userDTO.getUsername());
        applicationUser.setPassword(userDTO.getPassword());
        applicationUser.setUserRole(UserRoleEnum.ADMIN);
        return applicationUser;
    }
}
