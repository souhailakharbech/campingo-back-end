package webuild.esprit.tn.tunisiacampwebapplication.Paiement;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Order {
    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

}
