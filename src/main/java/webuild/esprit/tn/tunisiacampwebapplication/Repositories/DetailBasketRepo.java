package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Basket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.DetailBasket;

public interface DetailBasketRepo extends JpaRepository<DetailBasket, Integer> {
}
