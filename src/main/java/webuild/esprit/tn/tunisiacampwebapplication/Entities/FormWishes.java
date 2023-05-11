package webuild.esprit.tn.tunisiacampwebapplication.Entities;

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
public class FormWishes implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idFormWish")
    private Integer idFormWish;
    private String location;
    private Integer membernumber;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    private Date dateFin;
    private Boolean confirmed;
    private Boolean rejected;



}
