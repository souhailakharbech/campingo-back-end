package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idActivity")
    private Integer idActivity;
    private String name;
    private Double rate;
    private String description;
    @Enumerated(EnumType.STRING)
    private ActivityType activitytype;
    @Enumerated(EnumType.STRING)
    private Activitesaison activitesaison;

    @ManyToMany(mappedBy="activities", cascade = CascadeType.ALL)
    private Set<Event> events;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="activite")
    private Set<ActivityFav> activityFavSet;
    @JsonIgnore
    @ManyToOne
    Campsites campsite;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="activity")
    private Set<Rating> ratings;





}
