package preonboarding.wanted.backend.domain.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.domain.recruitment.dto.ApplicationDto;
import preonboarding.wanted.backend.domain.recruitment.model.Application;
import preonboarding.wanted.backend.domain.recruitment.repository.ApplicationRepository;
import preonboarding.wanted.backend.domain.recruitment.repository.RecruitmentRepository;
import preonboarding.wanted.backend.domain.user.repository.PersonalUserRepository;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final PersonalUserRepository personalUserRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, RecruitmentRepository recruitmentRepository, PersonalUserRepository personalUserRepository) {
        this.applicationRepository = applicationRepository;
        this.recruitmentRepository = recruitmentRepository;
        this.personalUserRepository = personalUserRepository;
    }

    public void apply(ApplicationDto applicationDto) {
        applicationRepository.save(Application.builder()
                .recruitmentId(recruitmentRepository.findById(applicationDto.accountId()).orElseThrow(() -> new IllegalArgumentException("해당 공고가 없습니다.")))
                .accountId(personalUserRepository.findById(applicationDto.accountId()).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다.")).getUser())
                .build()
        );
    }
}
