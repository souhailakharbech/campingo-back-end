package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PreEvent;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.*;
import webuild.esprit.tn.tunisiacampwebapplication.api.SendMailService;


import javax.mail.MessagingException;
import java.util.*;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class OneUserWishService {
    @Autowired
    OneUserWishRepo ouwR;
    @Autowired
    Iuser userR;
    @Autowired
    PreEventRepo eveR;
    @Autowired
    CampsitesRepo campR;
    @Autowired
    SendMailService mailer;

    public List<OneUserWish> retrieveAlluserwishes() {
        return ouwR.findAll() ;
    }

    public OneUserWish updateuserwish(OneUserWish w ) {
        return ouwR.save(w);
    }


    public OneUserWish adduserwish(OneUserWish u, Integer iduser) {
        OneUserWish u2 = ouwR.save(u);
        User usr = userR.findById(iduser).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u2.setUser(usr);
        //ouwR.save(u2);
        return ouwR.save(u2);
    }


    public OneUserWish retrieveuserwish(Integer idusrwish) {
        return ouwR.findById(idusrwish).get();
    }

    public void removeuserwisht(Integer iduserwish) {
        ouwR.deleteById(iduserwish);
    }


    public void createpreevent (String location) throws MessagingException {
        List<OneUserWish> allwishesbylocation = ouwR.findByLocationLike(location);
        List<OneUserWish> wishesbylocationnotarchived = new ArrayList<>();
        for (OneUserWish u : allwishesbylocation){ if(u.isArchived() == false){wishesbylocationnotarchived.add(u);}}

        if (wishesbylocationnotarchived.size() >3){
            PreEvent preevent= new PreEvent();
            preevent.setOneUserWishs(new ArrayList<>());
            eveR.save(preevent);
             for(OneUserWish u : wishesbylocationnotarchived){ u.setArchived(true) ; mailer.sendmail(u.getUser().getEmail(),"CAMPINGO APP","WE GATHERED A GROUPE THAT WISHING TO GO TO THE SAME LOCATION AS YOU ");ouwR.save(u);}
           // eveR.save(preevent);
           preevent.getOneUserWishs().addAll(wishesbylocationnotarchived);

             preevent.setLocation(location);
           //  List<User> owner = userR.findownerbylocation(location);
            List<User> users = userR.findUsersWithCampsitesByLocation(location);
            User owner = null;
            for (User user : users) {
                if (user.getOwnercampsites() != null && !user.getOwnercampsites().isEmpty()) {
                    owner = user;
                    break;
                }
            }
            preevent.setOwnerhavethesamelocation(owner);
            mailer.sendmail(owner.getEmail(), "CAMPINGO APP"," NOTIFICATION OF RECIVING A PREEVENT ");
            eveR.save(preevent);
            // preevent.setOwnerhavethesamelocation(owner.get(0));

             eveR.save(preevent);

        }


    }
    @Scheduled(fixedDelay = 5000)
    public  void testpreeventexist() throws MessagingException {
        List<String> locations = ouwR.findDistinctLocations();
        for (String loc : locations){
            createpreevent(loc);
        }


    }






















    public List<Campsites> findNearestCampsites(double lat, double lon, double radius) {
        List<Campsites> allCampsites = campR.findAll() ; // query all campsites from the database
        List<Campsites> nearbyCampsites = new ArrayList<>();

        // convert latitude and longitude to radians
        double lat1 = Math.toRadians(lat);
        double lon1 = Math.toRadians(lon);

        for (Campsites campsite : allCampsites) {
            String[] locationArray = campsite.getLocation().split(",");
            double latitude = Double.parseDouble(locationArray[0]);
            double longitude = Double.parseDouble(locationArray[1]);
            // convert latitude and longitude to radians
            double lat2 = Math.toRadians(latitude);
            double lon2 = Math.toRadians(longitude);

            // calculate Haversine distance
            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;
            double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(dLon/2) * Math.sin(dLon/2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double distance = 6371 * c; // Earth's radius in km

            // add campsite to nearbyCampsites if it's within the radius
            if (distance <= radius) {
                nearbyCampsites.add(campsite);
            }
        }

        // sort nearby campsites by distance

//5.0 = 5km
        return nearbyCampsites;
    }


}
