package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCommande;
    private String ref;
    private float TotalAmount;
    private LocalDate CreationDate;
    @NonNull
    @OneToOne(mappedBy = "commande")
    private Invoice invoice;
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private Set<DetailBasket> detailBaskets;
}
