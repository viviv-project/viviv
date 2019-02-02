package ru.project.viviv.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.project.viviv.model.entity.Interest;

import javax.persistence.*;

@Entity
@Table(name = "vi_interest_connection")
@Data
public class InterestConnection {

    @Id
    @Column(name = "interest_connection_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Profile profile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "interest_id")
    private Interest interest;
}