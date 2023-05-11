package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostComments  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcmtr")
    private Integer idcmtr; // Clé primaire
    @Temporal(TemporalType.DATE)
    private Date dateaddcmtr;
    private String content;
    private Integer iduser;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PostComments> postCommentsSet;
}
