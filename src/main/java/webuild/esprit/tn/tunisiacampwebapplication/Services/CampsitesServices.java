package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CampsitesRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CampsitesServices implements ICampsitesServices {

    CampsitesRepo campsitesRepo;

Iuser userr;

    Iuser iuser;


    @Override
    public Campsites addCampsites(Campsites campsites) {
        return campsitesRepo.save(campsites);
    }
    @Override

    public Campsites addCampsitesuser(Campsites campsites,Integer id) {
        User user = userr.findById(id).get();
        campsites.setUser(user);
        return campsitesRepo.save(campsites);
    }

    @Override
    public List<Campsites> getAllCampsites() {
        return campsitesRepo.findAll();
    }

    @Override
    public Campsites findByIdCampsites(Integer IdCampsites) {
        return campsitesRepo.findById(IdCampsites).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public void removeCampsiteByid(Integer IdCampsite) {
        campsitesRepo.deleteById(IdCampsite);
    }

    @Override
    public Campsites updateCampsite(Integer id, Campsites campsites) {
        Campsites c = campsitesRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Campsite not found"));
        c.setLocation(campsites.getLocation());
        c.setName(campsites.getName());
        c.setDescription(campsites.getDescription());
        c.setRate(campsites.getRate());
        return campsitesRepo.save(c);
    }

    @Override
    public Campsites addCampsitesAndAssignOwner(Campsites campsites,Integer idUser) {
        User user = iuser.findById(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        campsites.setUser(user);
        return campsitesRepo.save(campsites);
    }

    @Override
    public void addCampsiteToFavorites(Integer userId, Integer campsiteId) {
        User user = iuser.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Campsites campsite = campsitesRepo.findById(campsiteId)
                .orElseThrow(() -> new ResourceNotFoundException("Campsite not found"));

        user.getFavoriteCampsites().add(campsite);
        iuser.save(user);
    }

    @Override
    public List<Campsites> filterCampsites(String location, String name, Double rate) {
        if (location == null && name == null && rate != null) {
            return campsitesRepo.findByRateGreaterThanOrRateEquals(rate, rate);
        } else if (location != null && name == null && rate == null) {
            return campsitesRepo.findByLocationContaining(location);
        } else if (location == null && name != null && rate == null) {
            return campsitesRepo.findByNameContaining(name);
        } else if (location != null && name != null && rate == null) {
            return campsitesRepo.findByLocationContainingOrNameContaining(location, name);
        } else if (location != null && name == null && rate != null) {
            return campsitesRepo.findByLocationContainingOrRateGreaterThanEqual(location, rate);
        } else if (location == null && name != null && rate != null) {
            return campsitesRepo.findByNameContainingOrRateGreaterThanEqual(name, rate);
        } else if (location != null && name != null && rate != null) {
            return campsitesRepo.findByLocationContainingOrNameContainingOrRateGreaterThanEqual(location, name, rate);
        } else {
            return null;
        }
        }}

