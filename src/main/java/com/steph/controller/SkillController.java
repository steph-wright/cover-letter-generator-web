package com.steph.controller;

import com.steph.entity.Skill;
import com.steph.entity.SkillId;
import com.steph.entity.UserDetails;
import com.steph.model.service.SkillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SkillController {

    @Autowired
    SkillService skillService;

    @RequestMapping("/manageSkills")
    public ModelAndView manageSkillsController(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        UserDetails user = (UserDetails) session.getAttribute("user");
        List<Skill> skills = skillService.findByUsername(user.getUsername());

        mv.addObject("skills", skills);
        mv.setViewName("manageSkills");
        return mv;
    }

    @RequestMapping("/addSkill")
    public ModelAndView addSkillController() {
        return new ModelAndView("addSkill");
    }

    @RequestMapping("/addedSkill")
    public ModelAndView addedSkillController(HttpSession session, @RequestParam("skillName") String skillName, @RequestParam("type") String type, @RequestParam("explanation") String explanation) {
        ModelAndView mv = new ModelAndView();
        UserDetails user = (UserDetails) session.getAttribute("user");

        boolean isTechnical;

        if (type == "technical")
            isTechnical = true;
        else
            isTechnical = false;

        SkillId skillId = new SkillId(user.getUsername(), skillName);
        Skill newSkill = new Skill(skillId, isTechnical, explanation);

        Skill addedSkill = skillService.createSkill(newSkill);
        String message = null;

        if (addedSkill == null) {
            message = "A skill with that name already exists.";
        } else {
            message = "Skill successfully added.";
        }

        mv.setViewName("addSkill");
        mv.addObject("message", message);
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView editSkillController(@RequestParam("skillUser") String username, @RequestParam("skillName") String skillName) {
        ModelAndView mv = new ModelAndView();

        Skill skill = skillService.readSkill(new SkillId(username, skillName));

        mv.addObject("skill", skill);
        mv.setViewName("edit");

        return mv;
    }

    @RequestMapping("/editSkill")
    public ModelAndView editedSkillController(@RequestParam("skillUser") String username, @RequestParam("skillName") String skillName, @RequestParam("type") String type, @RequestParam("explanation") String explanation, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Skill skill = skillService.readSkill(new SkillId(username, skillName));

        boolean isTechnical;

        if (type == "technical")
            isTechnical = true;
        else
            isTechnical = false;

        Skill editedSkill = new Skill(skill.getId(), isTechnical, explanation);

        skillService.updateSkill(editedSkill);

        String message = "The skill '" + skill.getId().getSkillName() + "' was successfully updated.";

        UserDetails user = (UserDetails) session.getAttribute("user");
        List<Skill> skills = skillService.findByUsername(user.getUsername());

        mv.addObject("skills", skills);
        mv.addObject("message", message);
        mv.setViewName("manageSkills");

        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteSkillController(@RequestParam("skillUser") String username, @RequestParam("skillName") String skillName) {
        ModelAndView mv = new ModelAndView();

        skillService.deleteSkill(new SkillId(username, skillName));

        List<Skill> skills = skillService.findByUsername(username);

        String message = "Skill '" + skillName + "' was deleted.";

        mv.addObject("skills", skills);
        mv.addObject("message", message);
        mv.setViewName("manageSkills");


        return mv;
    }


}
