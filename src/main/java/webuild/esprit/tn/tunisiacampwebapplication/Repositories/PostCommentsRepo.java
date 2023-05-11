package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PostComments;
@Repository
public interface PostCommentsRepo extends JpaRepository<PostComments, Integer> {
}
