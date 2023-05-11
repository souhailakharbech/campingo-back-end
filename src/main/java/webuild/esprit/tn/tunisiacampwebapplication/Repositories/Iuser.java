package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface Iuser extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);





   // Optional <User> findByOwnercampsiteslocation(String location);
   @Query("select distinct u from User u join fetch u.ownercampsites c where c.location = :location")
   List<User> findUsersWithCampsitesByLocation(@Param("location") String location);

    @Query("SELECT u FROM User u JOIN u.ownercampsites c WHERE c.location = :location")
    List<User> findownerbylocation(@Param("location") String location);



//List<User> findByOwnercampsiteslocation(String location);


}
