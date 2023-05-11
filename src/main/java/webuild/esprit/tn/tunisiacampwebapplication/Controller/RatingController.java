package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Rating;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IRatingService;
import webuild.esprit.tn.tunisiacampwebapplication.Services.RatingService;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/rate")
public class RatingController
{
    @Autowired
    IRatingService iRatingService;
    @PostMapping("/addRating/{activiteId}/{userid}")
    public Double addRating(@RequestBody Rating r,
                            @PathVariable("activiteId") Integer activiteId,
                            @PathVariable("userid")  Integer userid)
    {
        return iRatingService.addRating(r,activiteId,userid);
    }
}
