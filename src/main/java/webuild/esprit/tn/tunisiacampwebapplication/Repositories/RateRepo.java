package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Rating;

@Repository
public interface RateRepo extends JpaRepository<Rating,Integer>
{
    public Rating findByActivity_IdActivityAndUserrIdUser(Integer activityId,Integer userId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.activity.idActivity = :activityId")
    Double getAverageRatingForActivity(@Param("activityId") Integer activityId);
}
