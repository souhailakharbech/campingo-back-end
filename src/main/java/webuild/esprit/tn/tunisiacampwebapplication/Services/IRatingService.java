package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Rating;

import java.util.List;

public interface IRatingService
{
    List<Rating> retrieveAllRating();

    Double addRating (Rating r,Integer activiteId,Integer userid);

    Rating updateRating (Rating r);

    Rating retrieveRating(Integer idRating);



    void deleteRating(Integer idRating);
}
