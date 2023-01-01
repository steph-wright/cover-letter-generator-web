package com.steph.model.persistence;

import com.steph.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDao extends JpaRepository<UserDetails, String> {

    public UserDetails getUserDetailsByUsernameAndPassword(String username, String password);

}
