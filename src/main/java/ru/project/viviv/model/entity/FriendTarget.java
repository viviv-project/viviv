package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @JsonIgnore
    @NotNull
    @JoinColumn(name = "user_id")
    private Profile profile;

    @ManyToOne
    @JsonIgnore
    @NotNull
    @JoinColumn(name = "friend_source_id")
    private FriendSource friendSource;
}