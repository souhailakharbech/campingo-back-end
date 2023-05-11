package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityFavorites;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IActivityFavoriteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/activityfavorites")
public class ActivityFavoritesController
{
    IActivityFavoriteService iActivityFavoriteService;

    //localhost:8089/Campi/activityfavorites/retrieveAllActivityFavorite
    @GetMapping("/retrieveAllActivityFavorite")
    public List<ActivityFavorites> retrieveAllActivityFavorite() {
        List<ActivityFavorites> listActivityFavorite = iActivityFavoriteService.retrieveAllActivityFavorite();
        return listActivityFavorite;
    }


    //  http://localhost:8089/Campi/activityfavorites/retrieveActivityFavorite/1
    @GetMapping("/retrieveActivityFavorite/{idactivityfavorites}")
    public ActivityFavorites retrieveActivityFavorite(@PathVariable("idactivityfavorites") Integer idactivityfavorites) {
        return iActivityFavoriteService.retrieveActivityFavorite(idactivityfavorites);
    }

    // http://localhost:8089/Campi/activityfavorites/addActivityFavorite
    @PostMapping("/addActivityFavorite")
    public ActivityFavorites addActivityFavorite(@RequestBody ActivityFavorites af) {

        return iActivityFavoriteService.addActivityFavorite(af);
    }

    // http://localhost:8089/Campi/activityfavorites/deleteActivityFavorite/1
    @DeleteMapping("/deleteActivityFavorite/{idactivityfavorites}")
    public void deleteActivityFavorite(@PathVariable("idactivityfavorites") Integer idactivityfavorites) {
        iActivityFavoriteService.deleteActivityFavorite(idactivityfavorites);
    }


    // http://localhost:8089/Campi/activityfavorites/updateActivityFavorite
    @PutMapping("/updateActivityFavorite")
    public ActivityFavorites updateActivityFavorite(@RequestBody ActivityFavorites af) {
        ActivityFavorites activityFavorites= iActivityFavoriteService.updateActivityFavorite(af);
        return activityFavorites;
    }



}
