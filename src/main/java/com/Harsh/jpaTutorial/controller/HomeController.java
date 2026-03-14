package com.Harsh.jpaTutorial.controller;


import com.Harsh.jpaTutorial.model.Status;
import com.Harsh.jpaTutorial.model.User;
import com.Harsh.jpaTutorial.service.JobService;
import com.Harsh.jpaTutorial.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class HomeController {

    private final JobService jobService;
    private final UserService userService;

    public HomeController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        User user = userService.findByEmail(auth.getName());

        // ✅ pass all stats to dashboard
        model.addAttribute("userName", user.getName());
        model.addAttribute("total",     jobService.getTotalCount(user));
        model.addAttribute("applied",   jobService.getCountByStatus(user, Status.APPLIED));
        model.addAttribute("interview", jobService.getCountByStatus(user, Status.INTERVIEW));
        model.addAttribute("offer",     jobService.getCountByStatus(user, Status.OFFER));
        model.addAttribute("rejected",  jobService.getCountByStatus(user, Status.REJECTED));

        return "dashboard";
    }
}
