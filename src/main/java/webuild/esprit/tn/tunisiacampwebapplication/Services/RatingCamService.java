package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.RatingCamp;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CampsitesRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.RatingCamRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

public class RatingCamService implements IRatingCampService {

    CampsitesRepo campsitesRepo;
    RatingCamRepository ratingCamRepository;
    Iuser iuser;


    @Override
    public Double addRating(RatingCamp rating, Integer IdCampsites, Integer idUser) {
// Find the user and campsite entities
        User user = iuser.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found "));

        Campsites campsite = campsitesRepo.findById(IdCampsites)
                .orElseThrow(() -> new EntityNotFoundException("Campsite not found " ));

        // Set the user and campsite entities for the rating
        rating.setUser(user);
        rating.setCampsite(campsite);

        // Save the new rating
        ratingCamRepository.save(rating);

        // Recalculate the average rating for the campsite
        List<RatingCamp> ratings = ratingCamRepository.findByCampsite(campsite);
        double sum = 0;
        int count = 0;
        for (RatingCamp r : ratings) {
            sum += r.getValue();
            count++;
        }
        double newMoyRate = (count > 0) ? (sum / count) : 0.0;

        // Update the moyRate attribute of the campsite entity
        campsite.setMoyRate(newMoyRate);
        campsitesRepo.save(campsite);

        return newMoyRate;
    }
}
