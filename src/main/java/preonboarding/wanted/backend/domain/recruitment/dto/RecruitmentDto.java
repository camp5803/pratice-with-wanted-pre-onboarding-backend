package preonboarding.wanted.backend.domain.recruitment.dto;

import lombok.Builder;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;
import preonboarding.wanted.backend.domain.user.dto.EnterpriseUserDto;

@Builder
public record RecruitmentDto (

        Long id,
        @NonNull String position,
        @NonNull Long guarantee,
        @NonNull String content,
        @NonNull String techStack,
        EnterpriseUserDto enterpriseUserDto
) {
    public static RecruitmentDto of(Long id, @NonNull String position, @NonNull Long guarantee, @NonNull String content, @NonNull String techStack, EnterpriseUserDto enterpriseUserDto) {
        return new RecruitmentDto(id, position, guarantee, content, techStack, enterpriseUserDto);
    }

    public static RecruitmentDto from(@NonNull Recruitment recruitment) {
        return new RecruitmentDto(
                recruitment.getId(),
                recruitment.getPosition(),
                recruitment.getGuarantee(),
                recruitment.getContent(),
                recruitment.getTechStack(),
                EnterpriseUserDto.from(recruitment.getEnterpriseUser())
        );
    }

    public Recruitment toEntity() {
        return new Recruitment(
                id,
                position,
                guarantee,
                content,
                techStack,
                enterpriseUserDto.toEntity()
        );
    }
}