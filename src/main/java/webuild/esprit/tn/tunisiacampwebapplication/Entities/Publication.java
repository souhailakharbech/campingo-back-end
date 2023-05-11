package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpublication")
    private Integer idpublication;
    @Temporal (TemporalType.DATE)

    private Date datepublication;
    @Column(columnDefinition = "boolean default false")
    private boolean isarchived;
    private String content;
    private String location ;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private Set<PostComments> PostComments;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @JsonIgnore
    @OneToOne
    private Picture pictures  ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
    private List<Reaction> reactions;


}
