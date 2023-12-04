package preonboarding.wanted.backend.domain.user.dto;

import lombok.Builder;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.user.model.User;

@Builder
public record UserDto(
        long id,
        @NonNull String email,
        @NonNull String password
        ) {
    public static UserDto of(long id, @NonNull String email, @NonNull String password) {
        return new UserDto(id, email, password);
    }

    public static UserDto from(@NonNull User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public User toEntity() {
        return new User(
                id,
                email,
                password
        );
    }
}
