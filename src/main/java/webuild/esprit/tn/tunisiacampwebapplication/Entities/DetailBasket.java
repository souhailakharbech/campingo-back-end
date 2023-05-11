package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailBasket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetail;
    private Integer NbrperPiece;
    private Integer NbrPieceTotal;
    @NonNull

    private LocalDate PurchaseDate;
    @NonNull
    @ManyToOne
    Commande commande;
    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    Basket basket;
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailBasket")
    private Set<Tools> tools;

}
