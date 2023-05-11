package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Reaction;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Reaction;
import webuild.esprit.tn.tunisiacampwebapplication.Services.ReactionService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/Reaction")
public class ReactionController {
    ReactionService rs;

    @GetMapping("/retrieve-all-Reaction")
    public List<Reaction> getallReaction() {
        return  rs.retrieveAllReaction();
    }

    @GetMapping("/retrieve-Reaction/{Reaction-id}")
    public Reaction retrieveReactionh(@PathVariable("Reaction-id") Integer idReaction) {
        return rs.retrieveReaction(idReaction);}



    @PostMapping("/add-Reaction/{post_id}/{user_id}")
    public Reaction addReaction(@RequestBody Reaction e,@PathVariable("post_id") Integer post_id ,@PathVariable("user_id") Integer user_id) {
        Reaction Reaction = rs.addReaction(e,post_id,user_id);
        return Reaction;
    }


    @DeleteMapping("/remove-Reaction/{Reaction-id}")
    public void removeReaction(@PathVariable("Reaction-id") Integer idReaction) {
        rs.removeReactionbyid(idReaction);
    }


    @PutMapping("/update-Reaction")
    public Reaction updateReaction(@RequestBody Reaction e) {
        Reaction Reaction= rs.PostReaction(e);
        return Reaction;
    }




}
