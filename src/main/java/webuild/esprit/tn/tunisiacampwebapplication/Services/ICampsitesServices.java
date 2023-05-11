package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;

import java.util.List;

public interface ICampsitesServices {
    Campsites addCampsites(Campsites campsites);

    List<Campsites> getAllCampsites();

    Campsites findByIdCampsites(Integer IdCampsites);

    void removeCampsiteByid(Integer IdCampsite);

    Campsites updateCampsite(Integer id, Campsites campsites);

    public Campsites addCampsitesuser(Campsites campsites,Integer id);

    Campsites addCampsitesAndAssignOwner(Campsites campsites,Integer idUser);

    public void addCampsiteToFavorites(Integer userId, Integer campsiteId);

    List<Campsites> filterCampsites(String location, String name, Double rate);



}
