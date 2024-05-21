package team1.issuetracker.domain.milestone;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.issuetracker.domain.milestone.dto.MilestoneListResponse;
import team1.issuetracker.domain.milestone.dto.MilestoneMakeRequest;
import team1.issuetracker.domain.user.auth.Authenticator;

@RequestMapping("/milestone")
@Slf4j
@RestController
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final Authenticator authenticator;

    @Autowired
    public MilestoneController(MilestoneService milestoneService, Authenticator authenticator) {
        this.milestoneService = milestoneService;
        this.authenticator = authenticator;
    }

    @GetMapping("/list")
    public List<MilestoneListResponse> milestoneList() {
        return milestoneService.getList();
    }

    @PostMapping
    public Milestone createMilestone(@RequestBody MilestoneMakeRequest milestoneMakeRequest)
            throws IllegalArgumentException {
        return milestoneService.createMilestone(milestoneMakeRequest.name(), milestoneMakeRequest.description(),
                milestoneMakeRequest.deadline());
    }

    @PatchMapping("/{id}")
    public Milestone updateMilestone(@RequestBody MilestoneMakeRequest milestoneMakeRequest,
                                     @PathVariable("id") Long id, HttpServletRequest httpServletRequest)
            throws IllegalArgumentException {
        String userId = authenticator.authenticate(httpServletRequest);
        return milestoneService.updateMilestone(milestoneMakeRequest, id, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteMilestone(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        String userId = authenticator.authenticate(httpServletRequest);
        milestoneService.deleteMilestone(id, userId);
    }
}