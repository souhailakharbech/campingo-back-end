package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Complaint;

import java.util.List;

public interface IComplaint {
    Complaint addComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    Complaint findByIdComplaint(Integer idComplaint);

    void removeComplaintbyid(Integer idComplaint);

    Complaint updateComplaint(Complaint complaint);

    public void triaterComplaint(Complaint complaint);
    public boolean containsAnyWord(String description) ;
    public List<Complaint> trierReclamationsAlgorithmeSentimments(List<Complaint> complaints);
}
