package com.steph.model.service;

import com.steph.entity.Skill;
import com.steph.entity.SkillId;
import com.steph.model.persistence.SkillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillDao skillDao;


    @Override
    public Skill createSkill(Skill skill) {
        if (skillDao.existsById(skill.getId()))
            return null;
        else
            return skillDao.save(skill);
    }

    @Override
    public Skill readSkill(SkillId id) {
        return skillDao.getById(id);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillDao.save(skill);
    }

    @Override
    public boolean deleteSkill(SkillId id) {
        Skill skill = skillDao.getById(id);
        if (skill == null)
            return false;
        else
            skillDao.delete(skill);
            return true;
    }

    @Override
    public List<Skill> findByUsername(String username){
        return skillDao.findByIdUsername(username);
    }

    @Override
    public List<Skill> findMatchingByUsername(String username, String personSpecFull) {

        List<String> personSpec = List.of(personSpecFull.split(" "));

        List<Skill> allSkills = skillDao.findByIdUsername(username);

        List<Skill> matchingSkills = new ArrayList<>();

        for (String word: personSpec) {
            for (Skill skill: allSkills) {
                String skillName = skill.getId().getSkillName();
                if (StringUtils.containsIgnoreCase(word, skillName)) {
                    matchingSkills.add(skill);
                }
            }
        }
        return matchingSkills;
    }
}
