package preonboarding.wanted.backend.domain.recruitment.dto;

import lombok.NonNull;

public record ApplicationDto (
        @NonNull Long recruitmentId,
        @NonNull Long accountId
) {
    public static ApplicationDto of(@NonNull Long recruitmentId, @NonNull Long accountId) {
        return new ApplicationDto(recruitmentId, accountId);
    }
}
