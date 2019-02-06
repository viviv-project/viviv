package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vi_friend_source")
@Data
public class FriendSource {

    @Id
    @Column(name = "friend_source_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    @Enumerated(EnumType.STRING)
    private FriendStatus status = FriendStatus.REQUEST;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_source_id")
    @ToString.Exclude
    @JsonIgnore
    private List<FriendTarget> friendTargets = new ArrayList<>();
}