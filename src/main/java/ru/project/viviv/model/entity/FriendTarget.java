package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_friend_target")
@Data
public class FriendTarget {

    @Id
    @Column(name = "friend_target_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    @Enumerated(EnumType.STRING)
    private FriendStatus status = FriendStatus.REQUEST;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}