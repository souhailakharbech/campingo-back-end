package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityFavorites
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idactivityfavorites")
    private Integer idactivityfavorites;
    private Integer idactivity;
    //@ManyToMany(mappedBy="activityFavorites", cascade = CascadeType.ALL)
    //private Set<User> users;
}
