package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activitesaison;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ActivityType;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IActivityService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/activity")
public class ActivityController
{
    @Autowired
    IActivityService iActivityService;

    // http://localhost:8089/Campi/activity/retrieveAllActivity
    @GetMapping("/retrieveAllActivity")
    public List<Activity> retrieveAllActivity() {
        List<Activity> listActivity = iActivityService.retrieveAllActivity();
        return listActivity;
    }


    //  http://localhost:8089/Campi/activity/retrieveActivity/1
    @GetMapping("/retrieveActivity/{idActivity}")
    public Activity retrieveActivity(@PathVariable("idActivity") Integer idActivity) {
        return iActivityService.retrieveActivity(idActivity);
    }

    // http://localhost:8089/Campi/activity/addActivity
    @PostMapping("/addActivity/{campingID}")
    public Activity addActivity(@RequestBody Activity a,
                                @PathVariable("campingID") Integer campingID) {
        Activity activity = iActivityService.addActivity(a,campingID);
        return activity;
    }

    // http://localhost:8089/Campi/activity/deleteActivity/1
    @DeleteMapping("/deleteActivity/{idActivity}")
    public void deleteActivity(@PathVariable("idActivity") Integer idActivity) {
        iActivityService.deleteActivity(idActivity);
    }

    // http://localhost:8089/Campi/activity/updateActivity
    @PutMapping("/updateActivity")
    public Activity updateActivity(@RequestBody Activity a) {
        Activity activity= iActivityService.updateActivity(a);
        return activity;
    }
    @PostMapping("/Assignactivitytocampingsite/{activiteId}/{campingID}")
    public void Assignactivitytocampingsite(@PathVariable("activiteId") Integer activiteId,
                                            @PathVariable("campingID") Integer campingID)
    {
        iActivityService.Assignactivitytocampingsite(activiteId,campingID);

    }
    @PostMapping ("/ajouterActiviteFavorites/{activiteId}/{utilisateurId}")
    public void ajouterActiviteFavorites(@PathVariable("activiteId")Integer activiteId ,
                                         @PathVariable("utilisateurId")Integer utilisateurId) {
        iActivityService.ajouterActiviteFavorites(activiteId,utilisateurId);
    }
    @GetMapping ("/AlgorithmeDeSuggestionActivite/{userid}")
    public Activity algorithmeDeSuggestionActivite(@PathVariable("userid")Integer userid)
    {
        return iActivityService.algorithmeDeSuggestionActivite(userid);
    }


    @PostMapping("/upload-file")
    public String uploadimage(@RequestParam("file") MultipartFile file) throws Exception
    {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        String Path_Directory="C:\\Users\\marie\\Desktop\\ProjetSpringCampingo\\Campingo_web_application\\src\\main\\resources\\image";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        return "image uploaded successfully";
    }

    @GetMapping("/filter")
    public List<Activity> filtreActivity(@RequestParam(required = false)ActivityType activityType,
                                         @RequestParam(required = false) Double rate,
                                         @RequestParam(required = false)String location) {
        return iActivityService.filtreActivity(activityType,rate,location);
    }






}
