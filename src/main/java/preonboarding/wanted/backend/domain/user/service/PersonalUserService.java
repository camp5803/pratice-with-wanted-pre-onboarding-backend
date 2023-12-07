package preonboarding.wanted.backend.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.domain.user.dto.PersonalUserDto;
import preonboarding.wanted.backend.domain.user.dto.UserDto;
import preonboarding.wanted.backend.domain.user.model.PersonalUser;
import preonboarding.wanted.backend.domain.user.model.User;
import preonboarding.wanted.backend.domain.user.repository.PersonalUserRepository;
import preonboarding.wanted.backend.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class PersonalUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PersonalUserRepository personalUserRepository;

    @Autowired
    public PersonalUserService(PasswordEncoder passwordEncoder, UserRepository userRepository, PersonalUserRepository personalUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.personalUserRepository = personalUserRepository;
    }

    public PersonalUser join(UserDto userDto, PersonalUserDto personalUserDto) {
       User user = User.builder()
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .build();

        validateDuplicateUser(userDto.toEntity());
        PersonalUser personalUser = new PersonalUser(null, personalUserDto.name(), user);
        return personalUserRepository.save(personalUser);
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<PersonalUser> findPersonalUserById(Long id) {
        return personalUserRepository.findById(id);
    }

    public void deletePersonalUserById(Long id) {
        userRepository.deleteUserById(id);
    }

}
