package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PostComments;
import webuild.esprit.tn.tunisiacampwebapplication.Services.PostCommentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/PostComments")
public class PostCommentsController {
    PostCommentService pcs;
    @GetMapping("/retrieve-all-PostComments")
    public List<PostComments> getallPostComments() {
        return  pcs.retrieveAllPostComments();
    }

    @GetMapping("/retrieve-PostComments/{PostComments-id}")
    public PostComments retrievePostCommentsh(@PathVariable("PostComments-id") Integer idPostComments) {
        return pcs.retrievePostComments(idPostComments);}



    @PostMapping("/addComments_to_comment/{idcomment}/{iduser}")
    public PostComments addComments_to_comment(@RequestBody PostComments e,@PathVariable("idcomment")  Integer idcomment, @PathVariable("iduser") Integer iduser) {
        PostComments PostComments = pcs.addComments_to_comment(e,idcomment,iduser);
        return PostComments;
    }
    @PostMapping("/add-PostComments/{idpost}/{iduser}")
    public PostComments addPostComments(@RequestBody PostComments e,@PathVariable("idpost")  Integer idpost, @PathVariable("iduser") Integer iduser) {
        PostComments PostComments = pcs.addPostComments(e,idpost,iduser);
        return PostComments;
    }

    @DeleteMapping("/remove-PostComments/{PostComments-id}")
    public void removePostComments(@PathVariable("PostComments-id") Long idPostComments) {
        pcs.removeidPostComments(Math.toIntExact(idPostComments));
    }


    @PutMapping("/update-PostComments")
    public PostComments updatePostComments(@RequestBody PostComments e) {
        PostComments PostComments= pcs.updatePostComments(e);
        return PostComments;
    }



}
