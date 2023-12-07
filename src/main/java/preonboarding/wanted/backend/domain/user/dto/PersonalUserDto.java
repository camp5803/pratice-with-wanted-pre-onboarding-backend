package preonboarding.wanted.backend.domain.user.dto;

import lombok.Builder;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.user.model.PersonalUser;

@Builder
public record PersonalUserDto (
        Long id,
        @NonNull String name,
        UserDto userDto
) {
    public static PersonalUserDto of(Long id, @NonNull String name, UserDto userDto) {
        return new PersonalUserDto(id, name, userDto);
    }

    public static PersonalUserDto from(@NonNull PersonalUser personalUser) {
        return new PersonalUserDto(
                personalUser.getId(),
                personalUser.getName(),
                UserDto.from(personalUser.getUser())
        );
    }

    public PersonalUser toEntity() {
        return new PersonalUser(
                id,
                name,
                userDto.toEntity()
        );
    }
}