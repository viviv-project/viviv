package ru.project.viviv.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vi_image")
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    //todo переименовать image
    @Lob
    @Column
    @NotNull
    private byte[] image;
    @Column
    private String description;
    @Column
    private String name;
}