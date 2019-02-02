package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import ru.project.viviv.model.entity.InterestConnection;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vi_interest")
@Data
public class Interest {

    @Id
    @Column(name = "interest_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    @NotNull
    private String interest;

    @ToString.Exclude
    @OneToMany(mappedBy = "interest", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InterestConnection> interestConnections = new ArrayList<>();
}