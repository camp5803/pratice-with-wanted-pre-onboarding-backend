package preonboarding.wanted.backend.domain.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.domain.recruitment.dto.DetailRecruitmentDto;
import preonboarding.wanted.backend.domain.recruitment.dto.RecruitmentDto;
import preonboarding.wanted.backend.domain.recruitment.dto.RecruitmentResponseDto;
import preonboarding.wanted.backend.domain.recruitment.service.RecruitmentService;
import preonboarding.wanted.backend.domain.user.dto.EnterpriseUserDto;
import preonboarding.wanted.backend.domain.user.model.EnterpriseUser;
import preonboarding.wanted.backend.domain.user.service.EnterpriseUserService;

import java.util.List;

@Controller
@Transactional
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final EnterpriseUserService enterpriseUserService;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService, EnterpriseUserService enterpriseUserService) {
        this.recruitmentService = recruitmentService;
        this.enterpriseUserService = enterpriseUserService;
    }

    @GetMapping("/recruitment")
    @ResponseBody
    public List<RecruitmentResponseDto> getRecruitments() {
        return recruitmentService.getRecruitments();
    }

    @GetMapping("/recruitment/{id}")
    @ResponseBody
    public DetailRecruitmentDto getRecruitment(@PathVariable Long id) {
        return recruitmentService.getDetailRecruitment(id);
    }

    @GetMapping("/recruitment/search")
    @ResponseBody
    public List<RecruitmentResponseDto> searchRecruitment(@RequestParam("query") String query) {
        return recruitmentService.searchRecruitment(query);
    }

    @PostMapping("/recruitment")
    @ResponseBody
    @Transactional
    public RecruitmentResponseDto createRecruitment(
            @RequestParam("enterpriseUserId") Long enterpriseUserId,
            @RequestParam("position") String position,
            @RequestParam("guarantee") Long guarantee,
            @RequestParam("content") String content,
            @RequestParam("techStack") String techStack
    ) {
        EnterpriseUser enterpriseUser = enterpriseUserService.findEnterpriseUserById(enterpriseUserId).orElseThrow(
                () -> new IllegalArgumentException("기업 정보 없음")
        );

        RecruitmentDto recruitmentDto = RecruitmentDto.builder()
                .position(position)
                .guarantee(guarantee)
                .content(content)
                .techStack(techStack)
                .enterpriseUserDto(EnterpriseUserDto.from(enterpriseUser))
                .build();

        return recruitmentService.createRecruitment(recruitmentDto);
    }

    @PatchMapping("/recruitment/{id}")
    @ResponseBody
    public RecruitmentResponseDto updateRecruitment(
            @PathVariable Long id,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "guarantee", required = false) Long guarantee,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "techStack", required = false) String techStack
    ) {
        return recruitmentService.updateRecruitment(id, RecruitmentDto.builder()
                .id(id)
                .position(position)
                .guarantee(guarantee)
                .content(content)
                .techStack(techStack)
                .build());
    }

    @DeleteMapping("/recruitment/{id}")
    @ResponseBody
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
    }
}