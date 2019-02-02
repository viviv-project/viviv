package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vi_friend_source")
@Data
public class FriendSource {

    @Id
    @Column(name = "friend_source_id")
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id = UUID.randomUUID().toString();

    @Column
    @Enumerated(EnumType.STRING)
    private FriendStatus status = FriendStatus.REQUEST;

    @ManyToOne
    @JsonIgnore
    @NotNull
    @JoinColumn(name = "user_id")
    private Profile profile;

    @ToString.Exclude
    @NotNull
    @OneToMany(mappedBy = "friendSource", cascade = CascadeType.ALL)
    private List<FriendTarget> targetFriends = new ArrayList<>();
}