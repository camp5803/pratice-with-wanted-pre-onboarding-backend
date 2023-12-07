package preonboarding.wanted.backend.domain.user.dto;

import lombok.Builder;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;

@Builder
public record EnterpriseUserDto (
    Long id,
    @NonNull String name,
    @NonNull String country,
    @NonNull String region,
    UserDto userDto
) {
    public static EnterpriseUserDto of(Long id, @NonNull String name, @NonNull String country, @NonNull String region, UserDto userDto) {
        return new EnterpriseUserDto(id, name, country, region, userDto);
    }

    public static EnterpriseUserDto from(@NonNull EnterpriseUser enterpriseUser) {
        return new EnterpriseUserDto(
            enterpriseUser.getId(),
            enterpriseUser.getName(),
            enterpriseUser.getCountry(),
            enterpriseUser.getRegion(),
            UserDto.from(enterpriseUser.getUser())
        );
    }

    public EnterpriseUser toEntity() {
        return new EnterpriseUser(
            id,
            name,
            country,
            region,
            userDto.toEntity()
        );
    }
}