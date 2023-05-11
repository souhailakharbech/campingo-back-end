package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;

import java.util.List;


@Repository


public interface CampsitesRepo extends JpaRepository<Campsites,Integer>
{

    List<Campsites> findByLocationContaining(String location);
    List<Campsites> findByLocationContainingOrNameContaining(String location, String name);
    List<Campsites> findByLocationContainingOrRateGreaterThanEqual(String location, Double rate);
    List<Campsites> findByNameContaining(String name);
    List<Campsites> findByNameContainingOrRateGreaterThanEqual( String name, Double rate);
    List<Campsites> findByRateGreaterThanOrRateEquals(Double rate1, Double rate);
    List<Campsites> findByLocationContainingOrNameContainingOrRateGreaterThanEqual(String location, String name, Double rate);


}
