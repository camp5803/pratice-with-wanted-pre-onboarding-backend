package preonboarding.wanted.backend.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;

import java.util.Optional;

public interface EnterpriseUserRepository extends JpaRepository<EnterpriseUser, Long> {

    @NonNull
    <S extends EnterpriseUser>S save(@NonNull S enterpriseUser);

    @NonNull
    Optional<EnterpriseUser> findById(@NonNull Long id);
}
