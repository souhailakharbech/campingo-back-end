package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.*;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ActivityFavRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ActivityRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CampsitesRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityService implements IActivityService {

    @Autowired
    ActivityRepository activityRepository;
    CampsitesRepo campsitesRepo;
    ActivityFavRepo activityFavRepo;
    Iuser iuser;
    @Override
    public List<Activity> retrieveAllActivity()
    {
        return activityRepository.findAll();
    }

    @Override
    public Activity addActivity(Activity a,Integer campingID)
    {
        Campsites cmp = campsitesRepo.findById(campingID).get();
        a.setCampsite(cmp);
        return activityRepository.save(a);

    }

    @Override
    public Activity updateActivity(Activity a)
    {
        return activityRepository.save(a);
    }

    @Override
    public Activity retrieveActivity(Integer idActivity)
    {
        return activityRepository.findById(idActivity).get();
    }

    @Override
    public void deleteActivity(Integer idActivity)
    {
        activityRepository.deleteById(idActivity);

    }

    @Override
    public void Assignactivitytocampingsite(Integer activiteId, Integer campingID) {


            Activity activity=activityRepository.findById(activiteId).get();
            Campsites campsite=campsitesRepo.findById(campingID).get();

            activity.setCampsite(campsite);
            activityRepository.save(activity);


    }

    @Override
    public void ajouterActiviteFavorites(Integer activiteId, Integer utilisateurId)
    {
        User utilisateur =iuser.findById(utilisateurId).orElse(null);
        Activity activity =activityRepository.findById(activiteId).orElseThrow(null);

        if (utilisateur != null && activity != null) {
            Set<ActivityFav> activityFavs = utilisateur.getActivityFavs();
            if (activityFavs.stream().anyMatch(a -> a.getActivite().equals(activity))) {
                //le prédicat est a -> a.getActivite().equals(activity), qui vérifie si l'activité spécifiée (activity) est
                // égale à l'activité associée à chaque objet ActiviteFav (a) dans la liste.
                throw new RuntimeException("L'activité est déjà dans la liste des favoris de l'utilisateur");
            } else {

                ActivityFav activityFavorites = new ActivityFav();
                activityFavorites.setUtilisateur(utilisateur);
                activityFavorites.setActivite(activity);
                activityFavorites.setDateAjout(LocalDate.now());
                activityFavRepo.save(activityFavorites);
            }
        }
        else{
            throw new RuntimeException("Utilisateur ou activité non trouvée");
        }


    }

    @Override
    public Activity algorithmeDeSuggestionActivite(Integer userid)
    {
        User utilisateur = iuser.findById(userid).orElseGet(() -> null);
        List<Activity> listedesactivites = activityRepository.findAll();
        List<Activity> listeowneractivite = new ArrayList<>();
        if ( utilisateur!=null && (utilisateur.getUserType()== UserType.OWNER ))
        {
            for(Activity activity :listedesactivites)
            {
                System.err.println("hello World");
                if( activity.getCampsite() != null && activity.getCampsite().getUser() != null && activity.getCampsite().getUser().getIdUser() == userid)
                {
                    //return new Activity();
                }
                else
                {    System.err.println("hello World11");
                    listeowneractivite.add(activity);
                }
            }


            if (listeowneractivite.isEmpty())
            {
                return new Activity();
            }
            else {
                Random random = new Random();
                int randomIndex = random.nextInt(listeowneractivite.size());
                return listeowneractivite.get(randomIndex);
            }


        }
        else if (utilisateur!=null && (utilisateur.getUserType()==UserType.CLIENT))
        {
            if (listedesactivites == null || listedesactivites.isEmpty()) {
                return new Activity();
            }
            Random random1 = new Random();
            int randomIndex = random1.nextInt(listedesactivites.size());
            return listedesactivites.get(randomIndex);

        }

        System.err.println("hello World90");
        return new Activity();

    }

    @Override
    public List<Activity> filtreActivity(ActivityType activityType, Double rate, String location) {
        if(activityType==null &&location==null && rate !=null)
        {
            return activityRepository.findByRateGreaterThanEqual(rate);

        }
        else {
            if (activityType!=null && location==null && rate == null)
            {
                return activityRepository.findByActivitytype(activityType);

            } else {
                if (activityType==null && location != null && rate == null) {
                    return activityRepository.findByCampsiteLocation(location);
                } else {
                    if (activityType!=null && location != null && rate == null ){
                        return activityRepository.findByActivitytypeAndCampsiteLocation(activityType,location);
                    }
                    else {
                        if(activityType!=null && location == null && rate != null){
                            return activityRepository.findByActivitytypeAndRateGreaterThanEqual(activityType,rate);
                        }
                        else {
                            if(activityType==null && location != null && rate != null){
                                return activityRepository.findByRateGreaterThanEqualAndCampsiteLocation(rate,location);

                            }
                        }
                        return activityRepository.findByActivitytypeAndRateGreaterThanEqualAndCampsiteLocation(activityType,rate,location);
                    }


                }
            }
        }


    }


}
