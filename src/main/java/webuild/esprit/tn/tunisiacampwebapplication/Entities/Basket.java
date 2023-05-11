package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBasket;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "basket")
    private Set<DetailBasket> detailBaskets;
}
