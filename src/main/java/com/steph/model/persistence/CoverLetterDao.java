package com.steph.model.persistence;

import com.steph.entity.CoverLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoverLetterDao extends JpaRepository<CoverLetter, Integer> {

    List<CoverLetter> findCoverLettersByUsername(String username);
}
