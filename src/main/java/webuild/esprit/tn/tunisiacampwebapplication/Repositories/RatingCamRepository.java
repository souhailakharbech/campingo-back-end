package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.RatingCamp;

import java.util.List;

public interface RatingCamRepository extends JpaRepository<RatingCamp,Integer> {

    List<RatingCamp> findByCampsite(Campsites campsite);}
