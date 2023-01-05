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


    @Override
    public CoverLetter generateCoverLetter(UserDetails user, List<Skill> matchingSkills, String roleName, String companyName) {
        // generate intro
        String intro = "I was so excited to see the role of " + roleName +
                " advertised with " + companyName + ". Your company values have resonated with me and I hope you will consider my application.";

        // generate body
        String body = new String();
        int i = 0;

        for (Skill skill: matchingSkills) {

            if (i > hardSkillIntros.length)
                i = 0;

            body += hardSkillIntros[i] + skill.getId().getSkillName() +
                    ", for example, " + skill.getExplanation() + " ";

            i++;
        }

        // generate sign-off
        String signOff = user.getFirstName() + " " + user.getLastName();


        CoverLetter coverLetter = new CoverLetter(roleName, companyName, user.getUsername(), intro, body, signOff);

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
