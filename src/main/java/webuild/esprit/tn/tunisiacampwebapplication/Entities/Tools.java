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
public class Tools implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTools;
    private String Type;
    private String Brand;
    private String Category;
    private String Description;
    private int Promotion;
    private float priceperUnit;
    private Integer quantity;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tools_images", joinColumns = {
            @JoinColumn(name = "tools_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name ="image_id"  )
    })
    private Set<ImageModel> toolsImages;

    public Set<ImageModel> getToolsImages() {
        return toolsImages;
    }

    public void setToolsImages(Set<ImageModel> toolsImages) {
        this.toolsImages = toolsImages;
    }

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @NonNull
    @ManyToOne
    DetailBasket detailBasket;
}
