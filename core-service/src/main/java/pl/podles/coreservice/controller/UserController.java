package pl.podles.coreservice.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.podles.coreservice.dto.UserDTO;
import pl.podles.coreservice.mapper.UserMapper;
import pl.podles.coreservice.model.UserRoleEnum;
import pl.podles.coreservice.security.ApplicationUserRepository;

@RestController
public class UserController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserRepository applicationUserRepository;

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }


    @PostMapping("/sign-up-admin")
    public void signUpAdmin(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        applicationUserRepository.save(UserMapper.toEntity(userDTO, UserRoleEnum.ADMIN));
    }

    @PostMapping("/sign-up-customer")
    public void signUpCustomer(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        applicationUserRepository.save(UserMapper.toEntity(userDTO,UserRoleEnum.CUSTOMER));
    }

}
