package preonboarding.wanted.backend.domain.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @NonNull
    <S extends Recruitment>S save(@NonNull S recruitment);

    @NonNull
    Optional<Recruitment> findById(@NonNull Long id);

    @NonNull
    Optional<Recruitment> findByPositionContaining(@NonNull String title);

    @NonNull
    Optional<Recruitment> findByTechStackContaining(@NonNull String techStack);

    @NonNull
    Optional<Recruitment> findByContentContaining(@NonNull String content);

    @NonNull
    List<Recruitment> findAll();

    void deleteById(@NonNull Long id);
}
