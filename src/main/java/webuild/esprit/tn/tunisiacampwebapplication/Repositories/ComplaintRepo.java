package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Complaint;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ComplaintType;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.EtatComplaint;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {
    Integer countByComplaintTypeAndEtatComplaint(ComplaintType complaintType, EtatComplaint complaintEtat);
}
