package com.steph.model.service;

import com.steph.entity.CoverLetter;
import com.steph.entity.Skill;
import com.steph.entity.UserDetails;
import com.steph.model.persistence.CoverLetterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    @Autowired
    private CoverLetterDao coverLetterDao;

    private String[] hardSkillIntros = {"I possess expertise in ", "I am passionate about ",
            "I can offer skills in ", "I am proficient in ",
            "I have a strong background in ", "Throughout my career I have acquired a very good knowledge of ",
            "I am adept at ", "I have a proven record of excelling at "};

    private String[] softSkillIntros = {"Throughout my career I have prided myself on my excellent skills in ",
            "I consistently seek to develop and improve my skills in ",
            "As a person I have a natural capability for "};


    @Override
    public CoverLetter generateCoverLetter(UserDetails user, List<Skill> matchingSkills, String roleName, String companyName) {
        // generate intro
        String intro = "I was so excited to see the role of " + roleName +
                " advertised with " + companyName + ". Your company values have resonated with me and I hope you will consider my application.";

        // generate hard skills section
        String hardSkills = new String();
        int i = 0;

        for (Skill skill: matchingSkills) {
            if (!skill.isTechnical())
                continue;

            if (i > hardSkillIntros.length)
                i = 0;

            hardSkills += hardSkillIntros[i] + skill.getId().getSkillName() +
                    ", for example, " + skill.getExplanation() + " ";

            i++;
        }

        // generate soft skills section
        String softSkills = new String();
        int x = 0;

        for (Skill skill: matchingSkills) {
            if (skill.isTechnical())
                continue;

            if (x > softSkillIntros.length)
                x = 0;

            hardSkills += softSkillIntros[x] + skill.getId().getSkillName() +
                    ", for example, " + skill.getExplanation() + " ";

            x++;
        }

        // generate sign-off
        String signOff = user.getFirstName() + " " + user.getLastName();


        CoverLetter coverLetter = new CoverLetter(roleName, companyName, user.getUsername(), intro, hardSkills, softSkills, signOff);

        return coverLetter;
    }

    @Override
    public CoverLetter saveCoverLetter(CoverLetter coverLetter) {
        return coverLetterDao.save(coverLetter);
    }

    @Override
    public List<CoverLetter> getAllCoverLettersByUsername(String username) {
        return coverLetterDao.findCoverLettersByUsername(username);
    }
}
