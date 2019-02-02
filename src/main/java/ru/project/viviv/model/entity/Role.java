//package ru.project.viviv.model.entity;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import lombok.ToString;
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "vi_role")
//@Data
//public class Role {
//
//    @Id
//    @Column(name = "role_id",unique = true, nullable = false)
//    String role_id;
//
//    @Column(unique = true, nullable = false)
//    String name;
//
//    @JsonIgnore
//    @ToString.Exclude
//    @ManyToMany(mappedBy = "roles")
//    List<User> users = new ArrayList<>();
//
//}
