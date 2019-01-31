package ru.project.viviv.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vi_images")
@Data
public class Image {

    @Id
    private String image_id = UUID.randomUUID().toString();

    @Column
    private String image;
    @Column
    private String description;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Profile profile;
}
