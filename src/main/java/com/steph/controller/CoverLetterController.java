package com.steph.controller;

import com.steph.entity.CoverLetter;
import com.steph.entity.Skill;
import com.steph.entity.UserDetails;
import com.steph.model.service.CoverLetterService;
import com.steph.model.service.SkillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CoverLetterController {

    @Autowired
    CoverLetterService coverLetterService;

    @Autowired
    SkillService skillService;

    @RequestMapping("/generate")
    public ModelAndView generateCoverLetterController() {
        return new ModelAndView("generateCoverLetter");
    }

    @RequestMapping("/displayCoverLetter")
    public ModelAndView displayCoverLetterController(@RequestParam("roleName") String roleName, @RequestParam("companyName") String companyName, @RequestParam("personSpec") String personSpec, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        UserDetails user = (UserDetails) session.getAttribute("user");

        List<Skill> matchingSkills = skillService.findMatchingByUsername(user.getUsername(), personSpec);

        CoverLetter coverLetter = coverLetterService.generateCoverLetter(user, matchingSkills, roleName, companyName);

        coverLetterService.saveCoverLetter(coverLetter);

        mv.addObject("coverLetter", coverLetter);
        mv.setViewName("displayCoverLetter");

        return mv;
    }

    @RequestMapping("/viewCoverLetters")
    public ModelAndView viewCoverLettersController(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        UserDetails user = (UserDetails) session.getAttribute("user");

        List<CoverLetter> coverLetters = coverLetterService.getAllCoverLettersByUsername(user.getUsername());

        mv.addObject("coverLetters", coverLetters);
        mv.setViewName("viewCoverLetters");

        return mv;
    }
}
