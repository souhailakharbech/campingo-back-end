package webuild.esprit.tn.tunisiacampwebapplication.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder


public class
Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEvent")
    private Integer idEvent; // Clé primaire
    private String location;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = " DATE DEFAULT CURRENT_DATE")
    private Date dateEvent;
    private Integer numberMaxOfUsers;
    private float prix;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Activity> activities;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<User> users;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User owner;



}
