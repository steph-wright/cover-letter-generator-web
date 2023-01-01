package com.steph.model.persistence;

import com.steph.entity.Skill;
import com.steph.entity.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillDao extends JpaRepository<Skill, SkillId> {


    public Skill getById(SkillId id);

    public List<Skill> findByIdUsername(String username);

}
