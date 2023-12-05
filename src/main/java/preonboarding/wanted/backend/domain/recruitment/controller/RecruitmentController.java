package preonboarding.wanted.backend.domain.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import preonboarding.wanted.backend.domain.recruitment.model.Recruitment;
import preonboarding.wanted.backend.domain.recruitment.service.RecruitmentService;

@Controller
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @Autowired
    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("/recruitment")
    @ResponseBody
    public Iterable<Recruitment> getRecruitments() {
        return recruitmentService.getRecruitments();
    }

    @GetMapping("/recruitment/{id}")
    @ResponseBody
    public Recruitment getRecruitment(@PathVariable Long id) {
        return recruitmentService.getRecruitment(id);
    }

    @PostMapping("/recruitment")
    @ResponseBody
    public Recruitment createRecruitment(
            @RequestParam("position") String position,
            @RequestParam("guarantee") Long guarantee,
            @RequestParam("content") String content,
            @RequestParam("techStack") String techStack
    ) {
        Recruitment recruitment = Recruitment.builder()
                .position(position)
                .guarantee(guarantee)
                .content(content)
                .techStack(techStack)
                .build();

        return recruitmentService.createRecruitment(recruitment);
    }

    @PatchMapping("/recruitment/{id}")
    @ResponseBody
    public Recruitment updateRecruitment(
            @PathVariable Long id,
            @RequestParam("position") String position,
            @RequestParam("guarantee") Long guarantee,
            @RequestParam("content") String content,
            @RequestParam("techStack") String techStack
    ) {
        Recruitment originalRecruitment = recruitmentService.getRecruitment(id);
        Recruitment modifiedRecruitment = Recruitment.builder()
                .id(id)
                .position(position != null ? position : originalRecruitment.getPosition())
                .guarantee(guarantee != null ? guarantee : originalRecruitment.getGuarantee())
                .content(content != null ? content : originalRecruitment.getContent())
                .techStack(techStack != null ? techStack : originalRecruitment.getTechStack())
                .build();

        return recruitmentService.updateRecruitment(modifiedRecruitment);
    }

    @DeleteMapping("/recruitment/{id}")
    @ResponseBody
    public void deleteRecruitment(@PathVariable Long id) {
        recruitmentService.deleteRecruitment(id);
    }
}