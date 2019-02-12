package ru.project.viviv.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vi_role")
@Data
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleStatus status = RoleStatus.USER;
}