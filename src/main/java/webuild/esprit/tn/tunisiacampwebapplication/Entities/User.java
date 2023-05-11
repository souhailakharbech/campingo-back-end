package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String nameUser  ;
    private String familyName  ;
    private Date birthday ;
    private String adress ;
    private String email  ;
    private String password  ;
    private Boolean isBlocked;
    private Boolean isActive;
    @Enumerated(EnumType.STRING)
    private UserType userType ;
    private Integer phoneNumber;
    private Integer loyaltyPoints;

    private int numberOfAlerts;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
    private List<Complaint> complaints;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private List<Complaint> complaints1;


    //@ManyToMany(cascade = CascadeType.ALL)
    //private Set<Activity> activityFavorites;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "favorite_campsites",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "IdCampsite"))

    private List<Campsites> favoriteCampsites = new ArrayList<>();

    @JsonIgnore
     @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
     private List<Campsites> ownercampsites;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="utilisateur")
    private Set<ActivityFav> activityFavs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userr")
    private Set<Rating> ratings;


    ///////////User_Event/////////
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;



  //  retourne la liste des autorisations (ou rôles) de l'utilisateur.
  @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<PreEvent> PreEvents;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Ajouter les rôles de l'utilisateur à la liste d'autorités
        authorities.add(new SimpleGrantedAuthority(userType.name()));
        return authorities ;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    //si le compte de l'utilisateur est expiré ou non.
    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }
   // si le compte de l'utilisateur est verrouillé ou non.
    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }
    //si les informations d'authentification de l'utilisateur sont expirées ou non.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
