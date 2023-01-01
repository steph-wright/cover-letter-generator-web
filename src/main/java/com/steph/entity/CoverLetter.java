package com.steph.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class CoverLetter {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String roleName;
    private String companyName;

    private String username;

    private String intro;
    private String body;
    private String signOff;

    public CoverLetter() {
    }

    public CoverLetter(String roleName, String companyName, String username, String intro, String body, String signOff) {
        this.companyName = companyName;
        this.username = username;
        this.intro = intro;
        this.body = body;
        this.signOff = signOff;
    }
}
