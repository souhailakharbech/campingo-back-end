package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PostComments;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Publication;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PostCommentsRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PublicationRepo;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class PostCommentService {
   @Autowired
    PostCommentsRepo postR;
    @Autowired
    Iuser userR;
    @Autowired
    PublicationRepo pubr;

    public List<PostComments> retrieveAllPostComments() {
        return postR.findAll() ;
    }

    public PostComments addPostComments(PostComments w , Integer idpost,Integer iduser) {
        Publication pub = pubr.findById(idpost).get();
        w.setIduser(iduser);
        return postR.save(w);
    }

    public PostComments addComments_to_comment (PostComments w , Integer idcomment,Integer iduser) {
      PostComments cm = postR.findById(idcomment).get();
      w.setIduser(iduser);
      postR.save(w);
      cm.getPostCommentsSet().add(w);
      postR.save(cm);


        return postR.save(w);
    }


    public PostComments updatePostComments(PostComments w) {
        return postR.save(w);
    }


    public PostComments retrievePostComments(Integer idPostComments) {
        return postR.findById(idPostComments).get();
    }

    public void removeidPostComments(Integer idPostComments) {

        postR.deleteById(idPostComments);
    }

}
