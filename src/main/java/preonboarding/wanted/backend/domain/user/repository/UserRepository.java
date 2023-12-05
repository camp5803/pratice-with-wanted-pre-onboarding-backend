package preonboarding.wanted.backend.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @NonNull
    <S extends User>S save(@NonNull S user);

    @NonNull
    Optional<User> findByEmail(@NonNull String email);

    void deleteUserById(@NonNull Long id);
}
