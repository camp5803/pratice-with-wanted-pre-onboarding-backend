package preonboarding.wanted.backend.domain.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "enterprise")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnterpriseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String region;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private User user;

    @Builder

    public EnterpriseUser(Long id, String name, String country, String region, User user) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.user = user;
    }
}
