package preonboarding.wanted.backend.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.user.model.PersonalUser;

import java.util.Optional;

public interface PersonalUserRepository extends JpaRepository<PersonalUser, Long> {

    @NonNull
    <S extends PersonalUser>S save(@NonNull S personalUser);

    @NonNull
    Optional<PersonalUser> findById(@NonNull Long id);
}
