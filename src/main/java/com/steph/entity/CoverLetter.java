package com.steph.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@Entity
public class CoverLetter {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String roleName;
    private String companyName;

    private String username;
    private String open = "To whom it may concern";

    @Lob
    @Column(columnDefinition = "longtext")
    private String intro;
    @Lob
    @Column(columnDefinition = "longtext")
    private String hardSkills;

    @Lob
    @Column(columnDefinition = "longtext")
    private String softSkills;

    private String yours = "Yours sincerely, ";

    @Lob
    @Column(columnDefinition = "longtext")
    private String signOff;

    private String dateCreated;

    public CoverLetter() {
    }

    public CoverLetter(String roleName, String companyName, String username, String intro, String hardSkills, String softSkills, String signOff) {
        this.roleName = roleName;
        this.companyName = companyName;
        this.username = username;
        this.intro = intro;
        this.hardSkills = hardSkills;
        this.softSkills = softSkills;
        this.signOff = signOff;
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        this.dateCreated = dateTime;
    }
}
