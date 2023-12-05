package preonboarding.wanted.backend.domain.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;
import preonboarding.wanted.backend.domain.recruitment.repository.RecruitmentRepository;

import java.util.List;

@Service
@Transactional
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Autowired
    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public Recruitment createRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment updateRecruitment(Recruitment recruitment) {
        return recruitmentRepository.save(recruitment);
    }

    public Recruitment getRecruitment(Long id) {
        return recruitmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공고가 없습니다."));
    }

    public List<Recruitment> getRecruitments() {
        return recruitmentRepository.findAll();
    }

    public void deleteRecruitment(Long id) {
        recruitmentRepository.deleteById(id);
    }
}
