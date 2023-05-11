package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activitesaison;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityType;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer>
{
    List<Activity> findByRateGreaterThanEqual(Double rate);
    List<Activity> findByCampsiteLocation(String location);
    List<Activity> findByActivitytype(ActivityType activityType);
    List<Activity> findByActivitytypeAndRateGreaterThanEqual(ActivityType activityType,Double rate);
    List<Activity> findByActivitytypeAndCampsiteLocation(ActivityType activityType,String location);
    List<Activity> findByRateGreaterThanEqualAndCampsiteLocation(Double rate,String location);
    List<Activity> findByActivitytypeAndRateGreaterThanEqualAndCampsiteLocation(ActivityType activityType,Double rate,String location);

}
