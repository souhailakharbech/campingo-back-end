package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;

import java.util.Optional;

@Repository
public interface PictureRepo extends JpaRepository<Picture,Integer> {

    Optional<Picture> findByUrl(String url);
}
