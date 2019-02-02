package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vi_images")
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    @NotNull
    private String image;
    @Column
    private String description;
    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Profile profile;
}