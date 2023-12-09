package preonboarding.wanted.backend.domain.recruitment.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.wanted.backend.domain.user.model.User;

@Getter
@Entity(name = "application")
@IdClass(ApplicationId.class)
@NoArgsConstructor
public class Application {

    @Id
    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitmentId;

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private User accountId;

    @Builder
    public Application(Recruitment recruitmentId, User accountId) {
        this.recruitmentId = recruitmentId;
        this.accountId = accountId;
    }
}
