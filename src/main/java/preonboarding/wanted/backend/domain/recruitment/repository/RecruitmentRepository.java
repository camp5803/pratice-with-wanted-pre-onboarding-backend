package preonboarding.wanted.backend.domain.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @NonNull
    <S extends Recruitment>S save(@NonNull S recruitment);

    @NonNull
    Optional<Recruitment> findById(@NonNull Long id);

    @NonNull
    List<Recruitment> findByEnterpriseUser(@NonNull EnterpriseUser enterpriseUser);

    @NonNull
    List<Recruitment> findByPositionContaining(@NonNull String title);

    @NonNull
    List<Recruitment> findByTechStackContaining(@NonNull String techStack);

    @NonNull
    List<Recruitment> findByContentContaining(@NonNull String content);

    @NonNull
    List<Recruitment> findAll();

    void deleteById(@NonNull Long id);
}
