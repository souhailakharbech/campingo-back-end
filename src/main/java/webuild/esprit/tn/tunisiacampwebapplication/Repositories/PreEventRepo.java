package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PreEvent;

@Repository
public interface PreEventRepo extends JpaRepository<PreEvent,Integer> {
}
