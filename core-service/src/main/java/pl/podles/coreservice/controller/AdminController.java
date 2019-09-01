package pl.podles.coreservice.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.podles.coreservice.dto.UserDTO;
import pl.podles.coreservice.mapper.UserMapper;
import pl.podles.coreservice.security.ApplicationUserRepository;

@RestController
public class AdminController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserRepository applicationUserRepository;

    public AdminController(BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }


    @PostMapping("/sign-up-admin")
    public void signUp(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        applicationUserRepository.save(UserMapper.toEntity(userDTO));
    }

}
