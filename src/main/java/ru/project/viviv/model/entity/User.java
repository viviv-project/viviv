package ru.project.viviv.model.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vi_user")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<RoleConnection> roleConnections = new ArrayList<>();
}