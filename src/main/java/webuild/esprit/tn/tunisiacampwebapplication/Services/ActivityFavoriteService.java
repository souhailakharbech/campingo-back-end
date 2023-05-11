package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityFavorites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ActivityFavoriteRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ActivityRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;


import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityFavoriteService implements IActivityFavoriteService{

    ActivityFavoriteRepo activityFavoriteRepo;
    ActivityRepository activityRepository;
    Iuser iuser;
    @Override
    public List<ActivityFavorites> retrieveAllActivityFavorite()
    {
        return activityFavoriteRepo.findAll() ;
    }

    @Override
    public ActivityFavorites addActivityFavorite(ActivityFavorites af)
    {
        return activityFavoriteRepo.save(af);
    }

    @Override
    public ActivityFavorites updateActivityFavorite(ActivityFavorites af)
    {
        return activityFavoriteRepo.save(af);
    }

    @Override
    public ActivityFavorites retrieveActivityFavorite(Integer idactivityfavorites)
    {
        return activityFavoriteRepo.findById(idactivityfavorites).get();

    }

    @Override
    public void deleteActivityFavorite(Integer idactivityfavorites)
    {
        activityFavoriteRepo.deleteById(idactivityfavorites);
    }

    @Override
    public void ajouterActiviteFavori(Integer activiteId, Integer utilisateurId)
    {

        Activity activite = activityRepository.findById(activiteId).orElseThrow(
                () -> new ResourceNotFoundException("Activity not found"));
        User utilisateur =iuser.findById(utilisateurId).orElseThrow(
                () -> new ResourceNotFoundException("user not found"));
        //utilisateur.getActivityFavorites().add(activite);
        //iuser.save(utilisateur);
        ActivityFavorites activiteFavorite = new ActivityFavorites();



    }
}
