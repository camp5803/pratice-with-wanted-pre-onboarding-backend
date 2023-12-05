package preonboarding.wanted.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.user.model.User;

@Getter
@Builder
public class EnterpriseUserDto {

    private Long id;
    @NonNull private String name;
    @NonNull private String country;
    @NonNull private String region;
    private User user;
}
