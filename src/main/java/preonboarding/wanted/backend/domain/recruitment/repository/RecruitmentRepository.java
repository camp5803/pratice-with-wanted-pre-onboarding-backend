package preonboarding.wanted.backend.domain.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;

import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @NonNull
    <S extends Recruitment>S save(@NonNull S recruitment);

    @NonNull
    Optional<Recruitment> findById(@NonNull Long id);

    void deleteById(@NonNull Long id);
}
