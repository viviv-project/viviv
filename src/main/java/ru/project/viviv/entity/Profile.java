package ru.project.viviv.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vi_profile")
@Data
public class Profile {

    @Id
    String user_id;
    @Column
    String firstname;
    @Column
    String lastname;
    @Column
    String middlename;
    @Column
    String avatar_image;
    @Column
    LocalDateTime date_added;
}