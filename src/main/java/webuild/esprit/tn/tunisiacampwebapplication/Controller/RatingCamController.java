package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.RatingCamp;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IRatingCampService;

@RestController
@AllArgsConstructor
@RequestMapping("/Rating")

public class RatingCamController {

     IRatingCampService iRatingCampService;

    @PostMapping("/addRating/{campsiteId}/{userId}")
    public ResponseEntity<Double> addRating(@RequestBody RatingCamp rating, @PathVariable Integer campsiteId, @PathVariable Integer userId){
        Double averageRating = iRatingCampService.addRating(rating, campsiteId, userId);
        return ResponseEntity.ok().body(averageRating);
    }
}
