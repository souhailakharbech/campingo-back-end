package webuild.esprit.tn.tunisiacampwebapplication.Entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComplaint;
    private String description  ;
    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType ;
    @Enumerated(EnumType.STRING)
    private EtatComplaint etatComplaint ;
    private int scoreSentiment;
    @JoinColumn(name = "client_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    User client;

    @JoinColumn(name = "owner_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    User owner;
}
