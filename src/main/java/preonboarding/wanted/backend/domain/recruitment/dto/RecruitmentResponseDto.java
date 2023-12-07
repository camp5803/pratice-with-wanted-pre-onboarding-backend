package preonboarding.wanted.backend.domain.recruitment.dto;

import lombok.Builder;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;

@Builder
public record RecruitmentResponseDto (
        @NonNull Long id,
        @NonNull String position,
        @NonNull Long guarantee,
        @NonNull String content,
        @NonNull String techStack
) {
    public static RecruitmentResponseDto of(Long id, @NonNull String position, @NonNull Long guarantee, @NonNull String content, @NonNull String techStack) {
        return new RecruitmentResponseDto(id, position, guarantee, content, techStack);
    }

    public static RecruitmentResponseDto from(@NonNull Recruitment recruitment) {
        return new RecruitmentResponseDto(
                recruitment.getId(),
                recruitment.getPosition(),
                recruitment.getGuarantee(),
                recruitment.getContent(),
                recruitment.getTechStack()
        );
    }
}
