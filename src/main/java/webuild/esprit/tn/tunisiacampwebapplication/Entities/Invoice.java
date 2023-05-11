package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvoice;
    private String Reference;
    private LocalDate CreationDate;
    @NonNull
    @OneToOne
    private Commande commande;
}
