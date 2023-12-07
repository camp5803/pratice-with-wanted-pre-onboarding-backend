package preonboarding.wanted.backend.domain.recruitment.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;

@Getter
@Entity(name = "recruitment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private Long guarantee;
    private String content;
    private String techStack;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "enterprise_id")
    private EnterpriseUser enterpriseUser;

    @Builder
    public Recruitment(Long id, String position, Long guarantee, String content, String techStack, EnterpriseUser enterpriseUser) {
        this.id = id;
        this.position = position;
        this.guarantee = guarantee;
        this.content = content;
        this.techStack = techStack;
        this.enterpriseUser = enterpriseUser;
    }
}
