package ru.project.viviv.entity;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vi_user")
@Data
public class User {

    @Id
    String user_id = UUID.randomUUID().toString();

    @Column(unique = true, nullable = false)
    String login;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @ToString.Exclude
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "vi_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
