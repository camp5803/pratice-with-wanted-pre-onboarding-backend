package preonboarding.wanted.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.user.model.User;

@Getter
@Builder
public class PersonalUserDto {

    private Long id;
    @NonNull private String name;
    @NonNull private User user;
}