package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_role_connection")
@Data
@NoArgsConstructor
public class RoleConnection {

    @Id
    @Column(name = "role_connection_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public RoleConnection(Role role){
        this.role = role;
    }
}