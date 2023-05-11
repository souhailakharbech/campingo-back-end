package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.OneUserWishRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Services.OneUserWishService;
import webuild.esprit.tn.tunisiacampwebapplication.Services.PreEventService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/oneuserwish")
public class OneUserWishController {
    @Autowired
    OneUserWishService ouws;
    @Autowired
    OneUserWishRepo ouwR;
    @Autowired
    PreEventService prev;

    @GetMapping("/retrieve-all-OneUserWish")
    public List<OneUserWish> getallOneUserWish() {
         return  ouws.retrieveAlluserwishes();
    }

    @GetMapping("/retrieve-OneUserWish/{OneUserWish-id}")
    public OneUserWish retrieveOneUserWish(@PathVariable("OneUserWish-id") Integer idOneUserWish) {
        return ouws.retrieveuserwish(idOneUserWish);}


    @GetMapping("/retrieve-userwishesbylocation")
    public List<OneUserWish>  get(@RequestParam("loc") String loc){
        return ouwR.findByLocationLike(loc);}

    @PostMapping("/add-OneUserWish/{iduser}")
    public OneUserWish addOneUserWish(@RequestBody OneUserWish e,@PathVariable ("iduser") Integer iduser) {
        OneUserWish OneUserWish = ouws.adduserwish(e,iduser);
        return OneUserWish;
    }

    @DeleteMapping("/remove-OneUserWish/{OneUserWish-id}")
    public void removeOneUserWish(@PathVariable("OneUserWish-id") Long idOneUserWish) {
        ouws.removeuserwisht(Math.toIntExact(idOneUserWish));
    }


    @PutMapping("/update-OneUserWish")
    public OneUserWish updateOneUserWish(@RequestBody OneUserWish e) {
        OneUserWish OneUserWish= ouws.updateuserwish(e);
        return OneUserWish;
    }

    @GetMapping("/findNearestCampsites")
    public List<Campsites>  findNearestCampsites(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("radius") double radius){
        return ouws.findNearestCampsites(lat,  lon,  radius);



    }



}
