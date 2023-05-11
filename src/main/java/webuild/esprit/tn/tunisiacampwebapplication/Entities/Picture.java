package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpicture")
    private Integer idpicture;
    private String name;
    private String url;

}
