package preonboarding.wanted.backend.domain.recruitment.dto;

import lombok.Builder;
import lombok.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;

import java.util.List;

@Builder
public record DetailRecruitmentDto(
        @NonNull Long id,
        @NonNull String name,
        @NonNull String country,
        @NonNull String region,
        @NonNull String position,
        @NonNull Long guarantee,
        @NonNull String content,
        @NonNull String techStack,
        @NonNull List<Long> recruitmentIds
        ) {
    public static DetailRecruitmentDto of(Long id, @NonNull String name, @NonNull String country, @NonNull String region, @NonNull String position, @NonNull Long guarantee, @NonNull String content, @NonNull String techStack, @NonNull List<Long> recruitmentIds) {
        return new DetailRecruitmentDto(id, name, country, region, position, guarantee, content, techStack, recruitmentIds);
    }

    public static DetailRecruitmentDto from(@NonNull Recruitment recruitment, @NonNull List<Long> recruitmentIds) {
        return new DetailRecruitmentDto(
                recruitment.getId(),
                recruitment.getEnterpriseUser().getName(),
                recruitment.getEnterpriseUser().getCountry(),
                recruitment.getEnterpriseUser().getRegion(),
                recruitment.getPosition(),
                recruitment.getGuarantee(),
                recruitment.getContent(),
                recruitment.getTechStack(),
                recruitmentIds
        );
    }
}
