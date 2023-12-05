package preonboarding.wanted.backend.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.domain.user.dto.EnterpriseUserDto;
import preonboarding.wanted.backend.domain.user.dto.PersonalUserDto;
import preonboarding.wanted.backend.domain.user.dto.UserDto;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;
import preonboarding.wanted.backend.domain.user.model.PersonalUser;
import preonboarding.wanted.backend.domain.user.model.User;
import preonboarding.wanted.backend.domain.user.service.EnterpriseUserService;
import preonboarding.wanted.backend.domain.user.service.PersonalUserService;
import preonboarding.wanted.backend.domain.user.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    private final PersonalUserService personalUserService;
    private final EnterpriseUserService enterpriseUserService;

    @Autowired
    public UserController(UserService userService, PersonalUserService personalUserService, EnterpriseUserService enterpriseUserService) {
        this.userService = userService;
        this.personalUserService = personalUserService;
        this.enterpriseUserService = enterpriseUserService;
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.login(email, password);
    }

    @PostMapping("/user/personal")
    @ResponseBody
    public PersonalUser createPersonalUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name
    ) {
        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .build();
        PersonalUserDto personalUserDto = PersonalUserDto.builder()
                .name(name)
                .build();

        return personalUserService.join(userDto, personalUserDto);
    }

    @PostMapping("/user/enterprise")
    @ResponseBody
    public EnterpriseUser createEnterpriseUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("country") String country,
            @RequestParam("region") String region
    ) {
        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .build();
        EnterpriseUserDto enterpriseUserDto = EnterpriseUserDto.builder()
                .name(name)
                .country(country)
                .region(region)
                .build();

        return enterpriseUserService.join(userDto, enterpriseUserDto);
    }

    @DeleteMapping("/user")
    @ResponseBody
    public void cancelUserAccount(@RequestParam("id") Long id) {
        personalUserService.deletePersonalUserById(id);
    }
}
