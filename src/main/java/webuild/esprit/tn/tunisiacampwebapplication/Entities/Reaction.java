package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idreaction")
    private Integer idreaction; // Clé primaire
    @Temporal(TemporalType.DATE)
    private Date datereaction;
    private Integer iduser;
    @Enumerated(EnumType.STRING)
    private Reactiontype reactiontype;
@JsonIgnore
    @ManyToOne
    Publication publication;
}
