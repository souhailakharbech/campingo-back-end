package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityFav;

@Repository
public interface ActivityFavRepo extends JpaRepository<ActivityFav,Integer> {
}
