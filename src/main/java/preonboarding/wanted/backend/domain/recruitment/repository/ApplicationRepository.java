package preonboarding.wanted.backend.domain.recruitment.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.wanted.backend.domain.recruitment.model.Application;
import preonboarding.wanted.backend.domain.recruitment.model.ApplicationId;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationId> {

    @NonNull
    <S extends Application>S save(@NonNull S application);
}
