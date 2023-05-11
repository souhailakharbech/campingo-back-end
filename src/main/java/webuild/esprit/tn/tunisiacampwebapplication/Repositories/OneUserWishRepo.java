package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;

import java.util.List;

@Repository
public interface OneUserWishRepo extends JpaRepository<OneUserWish,Integer> {


    List<OneUserWish> findByLocationLike(String location);
    @Query("SELECT DISTINCT e.location FROM OneUserWish e")
    List<String> findDistinctLocations();


}
