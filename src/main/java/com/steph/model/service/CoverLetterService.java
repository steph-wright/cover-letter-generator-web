package com.steph.model.service;

import com.steph.entity.CoverLetter;
import com.steph.entity.Skill;
import com.steph.entity.UserDetails;
import com.steph.model.persistence.CoverLetterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CoverLetterService {

   CoverLetter generateCoverLetter(UserDetails user, List<Skill> matchingSkills, String roleName, String companyName);

   CoverLetter saveCoverLetter(CoverLetter coverLetter);

   List<CoverLetter> getAllCoverLettersByUsername(String username);

}
