package preonboarding.wanted.backend.domain.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "personal")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private User user;

    @Builder
    public PersonalUser(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
