package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  ActivityFav implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idActivityFav")
    private Integer idActivityFav;

    @Column(name = "date_ajout")
    private LocalDate dateAjout;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_activite")
    private Activity activite;


}

