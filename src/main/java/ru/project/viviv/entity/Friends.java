package ru.project.viviv.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "vi_friends")
@Data
public class Friends {

    @Id
    @Column(unique = true, nullable = false)
    private String _id = UUID.randomUUID().toString();

    @Column(name = "friend_one_id",  nullable = false)
    private String friend_one_id;

    @Column(name = "friend_two_id", nullable = false)
    private String friend_two_id;

    @Column
    private Enum status;


}
