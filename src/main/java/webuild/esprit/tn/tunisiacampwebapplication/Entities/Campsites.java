package webuild.esprit.tn.tunisiacampwebapplication.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder

public class Campsites implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdCampsite")
    private Integer IdCampsites; // Clé primaire
    private String location;
    private String name;
    private String rate;
    private String description;
    //////AttributRate/////
    private Double moyRate;


    @JsonIgnore
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="campsite")
    @JsonIgnore
    private Set<Activity> activitiescamp;



}
