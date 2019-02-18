package ru.project.viviv.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "vi_friend")
@Data
@NoArgsConstructor
public class Friend {

    @Id
    @Column(name = "friend_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_source_id")
    private FriendSource friendSource;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_target_id")
    private FriendTarget friendTarget;

    public Friend(FriendSource friendSource, FriendTarget friendTarget) {
        this.friendSource = friendSource;
        this.friendTarget = friendTarget;
    }
}