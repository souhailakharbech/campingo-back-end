package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Rating;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ActivityRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.RateRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RatingService implements IRatingService
{
    ActivityRepository activityRepository;
    Iuser iuser;
    RateRepo rateRepo;

    @Override
    public List<Rating> retrieveAllRating() {
        return null;
    }

    @Override
    public Double addRating(Rating r, Integer activiteId, Integer userid)
    {

        Rating existingRating = rateRepo.findByActivity_IdActivityAndUserrIdUser(activiteId,userid);
        if(existingRating !=null)
        {
            existingRating.setScore(r.getScore());
            rateRepo.save(existingRating);
        }
        else {

            User user = iuser.findById(userid).get();
            r.setUserr(user);
            rateRepo.save(r);
        }

        Activity activity = activityRepository.findById(activiteId).get();
        r.setActivity(activity);
        Double average = rateRepo.getAverageRatingForActivity(activiteId);
        activity.setRate(average);
        activityRepository.save(activity);
        return average;
    }


    @Override
    public Rating updateRating(Rating r) {
        return null;
    }

    @Override
    public Rating retrieveRating(Integer idRating) {
        return null;
    }

    @Override
    public void deleteRating(Integer idRating) {

    }
}
