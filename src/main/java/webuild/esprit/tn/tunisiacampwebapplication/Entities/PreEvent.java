package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="preevent")
    private Integer preevent;
    @Temporal(TemporalType.DATE)
    @Column(columnDefinition = " DATE DEFAULT CURRENT_DATE")
    private Date datecreation   ;
    private String location;
    @Column(columnDefinition = "boolean default false")
    private boolean confirmed ;
    @Column(columnDefinition = "boolean default false")
    private boolean rejected ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<OneUserWish> OneUserWishs;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private User ownerhavethesamelocation;
}
