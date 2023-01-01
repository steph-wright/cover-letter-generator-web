package com.steph.model.service;

import com.steph.entity.Skill;
import com.steph.entity.SkillId;

import java.util.List;

public interface SkillService {

    Skill createSkill(Skill skill);

    Skill readSkill(SkillId id);

    Skill updateSkill(Skill skill);

    boolean deleteSkill(SkillId id);

    List<Skill> findByUsername(String username);

    List<Skill> findMatchingByUsername(String username, List<String> personSpec);


}
