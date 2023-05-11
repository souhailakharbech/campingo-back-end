package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Campsites;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.FormWishes;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CampsitesRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.FormWishesRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FormWishesServices implements IFormWishesServices
{

    @Autowired
            private JavaMailSender mailSender;
    FormWishesRepo formWishesRepo;

    CampsitesRepo campsitesRepo;
    Iuser iuser;


    @Override
    public List<FormWishes> retrieveAllFormWishes() {
        return formWishesRepo.findAll();
    }


    @Override
    public void addFormWishesAndSendToOwners(FormWishes f,Integer userId)
    {
        User user = iuser.findById(userId).get();
        formWishesRepo.save(f);
        List<Campsites> matchingOwners=campsitesRepo.findByLocationContaining(f.getLocation());
        for (Campsites campsite : matchingOwners) {
            SimpleMailMessage message = new SimpleMailMessage();
            User owner = campsite.getUser();
            message.setFrom("yassinemarzouki007@gmail.com");// sender
            message.setTo(owner.getEmail());
            message.setSubject("New FormWishes Submitted");
            message.setText("A new FormWishes has been submitted by user "+user.getNameUser() + " for " +f.getMembernumber() +
                    "persons  for the location " + f.getLocation() +
                    " from " + f.getDateDebut() + " to " + f.getDateFin());
            mailSender.send(message);


        }



    }

    @Override
    public FormWishes updateFormWishes(FormWishes f) {
        return formWishesRepo.save(f);
    }

    @Override
    public FormWishes retrieveFormWishes(Integer idFormWishes) {
        return formWishesRepo.findById( idFormWishes).get();
    }

    @Override
    public void deleteFormWishes(Integer idFormWishes) {
        formWishesRepo.deleteById( idFormWishes);

    }

}
