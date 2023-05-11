package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activitesaison;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityType;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;

import java.util.List;

public interface IActivityService
{
    List<Activity> retrieveAllActivity();

    Activity addActivity (Activity a,Integer campingID);

    Activity updateActivity (Activity a);

    Activity retrieveActivity(Integer idActivity);

    //Etudiant removeEtudiant(Long idEtudiant);

    void deleteActivity(Integer idActivity);
    public void Assignactivitytocampingsite(Integer activiteId, Integer campingID);
    public void ajouterActiviteFavorites(Integer activiteId, Integer utilisateurId);
    public Activity algorithmeDeSuggestionActivite(Integer userid);
    public List<Activity> filtreActivity(ActivityType activityType, Double rate, String location);


}
