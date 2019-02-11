package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_friend")
@Data
public class Friend {

    @Id
    @Column(name = "friend_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "friend_source_id")
    private FriendSource friendSource;

    @ManyToOne
    @JoinColumn(name = "friend_target_id")
    private FriendTarget friendTarget;
}