package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Basket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;

public interface CommandeRepo extends JpaRepository<Commande, Integer> {
}
