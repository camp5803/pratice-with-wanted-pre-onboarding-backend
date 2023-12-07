package preonboarding.wanted.backend.domain.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.wanted.backend.domain.recruitment.dto.RecruitmentDto;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;
import preonboarding.wanted.backend.domain.recruitment.repository.RecruitmentRepository;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;
import preonboarding.wanted.backend.domain.user.repository.EnterpriseUserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class RecruitmentService {

    private final EnterpriseUserRepository enterpriseUserRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Autowired
    public RecruitmentService(EnterpriseUserRepository enterpriseUserRepository, RecruitmentRepository recruitmentRepository) {
        this.enterpriseUserRepository = enterpriseUserRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public RecruitmentDto createRecruitment(RecruitmentDto recruitmentDto) {
        return RecruitmentDto.from(recruitmentRepository.save(recruitmentDto.toEntity()));
    }

    public RecruitmentDto updateRecruitment(RecruitmentDto recruitmentDto) {
        return RecruitmentDto.from(recruitmentRepository.save(recruitmentDto.toEntity()));
    }

    public RecruitmentDto getRecruitment(Long id) {
        return RecruitmentDto.from(
                recruitmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공고가 없습니다."))
        );
    }

    public List<RecruitmentDto> getRecruitments() {
        return convertToDto(recruitmentRepository.findAll());
    }

    public void deleteRecruitment(Long id) { // 이해 안 됨) 그냥 지워지면 되는거지 왜 꼭 연관을 끊어야 하는지
        Recruitment recruitment = recruitmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 공고가 없습니다."));
        disassociateEnterpriseUser(recruitment);
        recruitmentRepository.deleteById(id);
    }

    public List<RecruitmentDto> searchRecruitment(String query) {
        Optional<EnterpriseUser> enterpriseInformation = enterpriseUserRepository.findByNameContaining(query);
        List<Recruitment> byName = enterpriseInformation
                .map(recruitmentRepository::findByEnterpriseUser)
                .orElseGet(Collections::emptyList);
        List<Recruitment> byPosition = recruitmentRepository.findByPositionContaining(query);
        List<Recruitment> byTechStack = recruitmentRepository.findByTechStackContaining(query);
        List<Recruitment> byContent = recruitmentRepository.findByContentContaining(query);

        // List들을 통합하여 아래에서 중복 제거
        List<List<RecruitmentDto>> results = Stream.of(byName, byPosition, byTechStack, byContent)
                .map(this::convertToDto)
                .toList();

        return mergeAndDistinctLists(results);
    }

    private <T> List<T> mergeAndDistinctLists(List<List<T>> lists) {
        return lists.stream()
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private List<RecruitmentDto> convertToDto(List<Recruitment> recruitments) {
        return recruitments.stream()
                .map(RecruitmentDto::from)
                .toList();
    }

    private void disassociateEnterpriseUser(Recruitment recruitment) {
        recruitmentRepository.save(Recruitment.builder()
                .id(recruitment.getId())
                .enterpriseUser(null)
                .position(recruitment.getPosition())
                .guarantee(recruitment.getGuarantee())
                .content(recruitment.getContent())
                .techStack(recruitment.getTechStack())
                .build());
    }
}
