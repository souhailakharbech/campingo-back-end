package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;

import java.util.List;

public interface ICommandeServices {
    List<Commande> retrieveAllCommande();
    Commande addCommande(Commande c);
    Commande retrieveCommande (Integer idCommande);
    void deleteCommande(Integer idCommande);
}
