package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Services.ICampsitesServices;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IUserServices;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/DetailCampsites")
public class CampsitesController {
    ICampsitesServices campsitesServices;
    IUserServices userServices;

    Iuser iuser;

    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/add-Campsites
    @PostMapping("/add-Campsites")
    public Campsites addCampsites(@RequestBody Campsites campsites) {
        return campsitesServices.addCampsites(campsites);
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/getAllEvents
    @GetMapping("/getAllEvents")
    public List<Campsites> getAllCampsites() {
        return campsitesServices.getAllCampsites();
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/FindByIdCampsites/{id}
    @GetMapping("/FindByIdCampsites/{id}")
    public Campsites findByIdCampsites(@PathVariable(value = "id") Integer IdCampsites) {
        return campsitesServices.findByIdCampsites(IdCampsites);
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/CampsiteDelete/{id}
    @DeleteMapping("/CampsiteDelete/{id}")
    public void removeCampsiteByid(@PathVariable(value = "id") Integer IdCampsite) {
        campsitesServices.removeCampsiteByid(IdCampsite);
    }

    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/CampsitetUpdate/{id}
    @PutMapping("/CampsitetUpdate/{id}")
    public Campsites updateCampsite(@PathVariable(value = "id") Integer id, @Valid @RequestBody Campsites campsites) {
        return campsitesServices.updateCampsite(id, campsites);
    }
    //http://localhost:8092/Campi/AUTH/auth/DetailCampsites/add-Campsites/{userId}
    @PostMapping("/add-Campsites/{userId}")
    public Campsites addCampsitesAndAssignOwner(@RequestBody Campsites campsites, @PathVariable(value = "userId") Integer userId) {
        return campsitesServices.addCampsitesAndAssignOwner(campsites, userId);
    }
    @PostMapping("/{userId}/favorites/campsites/{campsiteId}")
    public void addCampsiteToFavorites(@PathVariable Integer userId, @PathVariable Integer campsiteId) {
        campsitesServices.addCampsiteToFavorites(userId, campsiteId);
    }
    @GetMapping("/filter")
    public List<Campsites> filterCampsites(@RequestParam(required = false) String location,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Double rate) {
        return campsitesServices.filterCampsites(location, name, rate);
    }

}
