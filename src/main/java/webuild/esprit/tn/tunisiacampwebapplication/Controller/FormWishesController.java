package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.FormWishes;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IFormWishesServices;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/formwihes")
public class FormWishesController
{
    IFormWishesServices iFormWishesServices;
    @PostMapping("/addFormWishes/{userId}")
    public void addFormWishesAndSendToOwners(@RequestBody FormWishes f,
                                                   @PathVariable("userId") Integer userId) {
        iFormWishesServices.addFormWishesAndSendToOwners(f,userId);

    }

}
