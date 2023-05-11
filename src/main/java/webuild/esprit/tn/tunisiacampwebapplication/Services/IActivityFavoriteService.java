package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityFavorites;

import java.util.List;

public interface IActivityFavoriteService
{
    List<ActivityFavorites> retrieveAllActivityFavorite();

    ActivityFavorites addActivityFavorite (ActivityFavorites af);

    ActivityFavorites updateActivityFavorite (ActivityFavorites af);

    ActivityFavorites retrieveActivityFavorite(Integer idactivityfavorites);

    //Etudiant removeEtudiant(Long idEtudiant);

    void deleteActivityFavorite(Integer idactivityfavorites);

    public void ajouterActiviteFavori(Integer activiteId, Integer utilisateurId);
}
