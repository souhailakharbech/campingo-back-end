package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Complaint;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IComplaint;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IUserServices;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Complaint")
public class ComplaintController {
    IUserServices iUserServices;
    IComplaint iComplaint;
    //http://localhost:8092/Campi/AUTH/auth/Complaint/add-complaint
    @PostMapping("/add-complaint")
    public Complaint addComplaint(@RequestBody Complaint complaint) {

        return iComplaint.addComplaint(complaint);
    }

    //http://localhost:8092/Campi/Complaint/getAllComplaints
    @GetMapping("/getAllComplaints")
    public List<Complaint> getAllComplaints() {

        return iComplaint.getAllComplaints();
    }

    //http://localhost:8092/Campi/Complaint/FindByIdComplaint/{id}
    @GetMapping("/FindByIdComplaint/{id}")
    public Complaint findByIdComplaint(@PathVariable(value = "id")Integer idComplaint){
        return iComplaint.findByIdComplaint(idComplaint) ;
    }

    //http://localhost:8092/Campi/Complaint/ComplaintUpdate/{id}
    @PutMapping("/ComplaintUpdate")
    public Complaint updateComplaint(@Valid @RequestBody Complaint complaint) {
        return iComplaint.updateComplaint( complaint);
    }
    //http://localhost:8092/Campi/Complaint/ComplaintDelete/{id}
    @DeleteMapping("/ComplaintDelete/{id}")
    public void removeComplaintbyid(@PathVariable(value = "id")Integer idComplaint) {
         iComplaint.removeComplaintbyid(idComplaint);

    }


    @PostMapping("/traiterComplaint")
    public ResponseEntity<Void> processComplaint(@RequestBody Complaint complaint) {
        iComplaint.triaterComplaint(complaint);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/complaints/sentiment")
    public List<Complaint> trierReclamationsAlgorithmeSentimments() {
        List<Complaint> complaints = iComplaint.getAllComplaints();
        return iComplaint.trierReclamationsAlgorithmeSentimments(complaints);
    }
}
