package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class OneUserWish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusrwish")
    private Integer idusrwish;

    // Clé primaire/
    @Temporal (TemporalType.DATE)
    @Column(columnDefinition = " DATE DEFAULT CURRENT_DATE")
   private Date dateadduserwish   ;
    private String location;
    @Column(columnDefinition = "boolean default false")
    private boolean confirmed ;
   @Column(columnDefinition = "boolean default false")
    private boolean rejected ;
    @Column(columnDefinition = "boolean default false")
    private boolean archived ;
    @PrePersist
    protected void onCreate() {
        dateadduserwish = new Date();
    }
    //the user that aded the wish
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    //list of owners whome will reseve the one user wish
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> owners;
}
