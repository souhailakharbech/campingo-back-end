package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.OneUserWish;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PreEvent;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.EventRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.OneUserWishRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PreEventRepo;
import webuild.esprit.tn.tunisiacampwebapplication.api.SendMailService;

import javax.mail.MessagingException;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class PreEventService {
    @Autowired
    PreEventRepo preEventRepo;
    @Autowired
    OneUserWishRepo ouwR;
    @Autowired
    Iuser userR;
    @Autowired
    EventRepo eveR;
    @Autowired
    SendMailService mailer;


    //accept preevent

    public void acceptpreevetbyowner(Integer idprevent) throws MessagingException {
        PreEvent pr = preEventRepo.findById(idprevent).get();
        pr.setConfirmed(true);
        List<OneUserWish> userwisher = pr.getOneUserWishs();
        mailer.sendmail(pr.getOwnerhavethesamelocation().getEmail(),"text confirmation for owner","content confirmation");
        for(OneUserWish us : userwisher){
            mailer.sendmail(us.getUser().getEmail(),"text confirmation","content confirmation");
        }

    }
    public void refusepreevent(Integer idprevent) throws MessagingException {
        PreEvent pr = preEventRepo.findById(idprevent).get();
        pr.setRejected(true);
        List<OneUserWish> userwisher = pr.getOneUserWishs();
        mailer.sendmail(pr.getOwnerhavethesamelocation().getEmail(),"text rejection for owner","content rejection");
        for(OneUserWish us : userwisher){
            mailer.sendmail(us.getUser().getEmail(),"text rejection","content rejection");
        }

    }




}
