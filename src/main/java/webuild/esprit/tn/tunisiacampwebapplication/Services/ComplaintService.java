package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.*;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ComplaintRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ComplaintService implements IComplaint {
    @Autowired
    ComplaintRepo complaintRepo;
    @Autowired
    Iuser iuser ;
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public Complaint addComplaint(Complaint complaint) {

        return complaintRepo.save(complaint);
    }
    @Override
    public List<Complaint> getAllComplaints() {

        return complaintRepo.findAll();
    }

    @Override
    public Complaint findByIdComplaint(Integer idComplaint) {
        return complaintRepo.findById(idComplaint).orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
    }
    @Override
    public void removeComplaintbyid(Integer idComplaint)
    {
        complaintRepo.deleteById(idComplaint);
    }
    @Override
    public Complaint updateComplaint( Complaint complaint) {
        return complaintRepo.save(complaint);
    }




    @Override
    public void triaterComplaint(Complaint complaint) {
        User client = iuser.findById(complaint.getClient().getIdUser()).orElse(null);
        User owner = iuser.findById(complaint.getOwner().getIdUser()).orElse(null);
        if (client.getUserType() != UserType.CLIENT) {
            throw new IllegalArgumentException("c'est pas un client");
        }
        if (owner.getUserType() != UserType.OWNER) {
            throw new IllegalArgumentException("c'est pas un owner ");
        }
        complaint.setClient(client);
        complaint.setOwner(owner);

        complaintRepo.save(complaint);
        switch (complaint.getComplaintType()){
            case ACTIVITYISSUES:
                boolean containsWords = containsAnyWord(complaint.getDescription());
                if (containsWords) {
                    complaint.setEtatComplaint(EtatComplaint.VALIDEE);
                    sendComplaintEmail(owner.getEmail(), "Avertissement", "Nos clients nous ont informé de certaines réclamations concernant vos activités. Vous travaillez actuellement à examiner de près ces problèmes afin de les résoudre le plus rapidement possible.");
                //    sendComplaintEmail(client.getEmail(), "Réclamation en cours de traité", "Nous sommes désolés pour les problèmes que vous avez rencontrés. Nous avons pris des mesures pour les résoudre et nous espérons que cela répondra à vos besoins.");
                }
                else {
                    complaint.setEtatComplaint(EtatComplaint.REFUSEE);
                    sendComplaintEmail(client.getEmail(), "Refus de votre Réclamtion", "Votre réclamtion a été refusée.");

                }
                complaintRepo.save(complaint);
                break;
            case OTHERS:
                complaint.setEtatComplaint(EtatComplaint.REFUSEE);
                Integer countRefusedComplaints = complaintRepo.countByComplaintTypeAndEtatComplaint(ComplaintType.OTHERS, EtatComplaint.REFUSEE);
                if(countRefusedComplaints>=3){
                    complaint.setEtatComplaint(EtatComplaint.VALIDEE);
                    sendComplaintEmail(owner.getEmail(), "Avertissement", "Nos clients nous ont informé de certaines réclamations concernant .......");
                 //   sendComplaintEmail(client.getEmail(), "Réclamtion validée", "Nous sommes désolées pour les problémes que vous avez recncontrés ,Nous avons pris des mesures pour les résoudre et nous espérons que cela répondra à vos besoins.");
                }
                else {
                    complaint.setEtatComplaint(EtatComplaint.REFUSEE);
                    sendComplaintEmail(client.getEmail(), "Refus de votre Réclamtion", "Votre réclamtion a été refusée.");
                }
                complaintRepo.save(complaint);
                break;

        }

    }

    @Override
    public boolean containsAnyWord(String description) {
        return description.contains("NAUTIQUE") || description.contains("LUDIQUE") || description.contains("INTELLECTUELLES")
                || description.contains("nautique")|| description.contains("ludique")|| description.contains("intellectuelles");
    }




    private void sendComplaintEmail(String to, String subject, String text) {
        if (javaMailSender == null) {
            throw new IllegalStateException("javaMailSender is null");
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


    @Override
    public List<Complaint> trierReclamationsAlgorithmeSentimments(List<Complaint> complaints) {
        String[] positiveWords = {"good", "great", "excellent", "happy", "satisfied"};
        String[] negativeWords = {"problem", "bad", "unsatisfactory", "uncomfortable", "dirty", "disappointing"};
        // Calculer le score de sentiment pour chaque réclamation
        for (Complaint complaint : complaints) {
            String description = complaint.getDescription();
            int scorePositif = 0;
            int scoreNegatif = 0;
            for (String word : positiveWords) {
                if (description.contains(word)) {
                    scorePositif++;
                }
            }
            for (String word : negativeWords) {
                if (description.contains(word)) {
                    scoreNegatif++;
                }
            }
            complaint.setScoreSentiment(scoreNegatif - scorePositif);
            complaint.setDescription(description);
            complaintRepo.save(complaint);

        }   // Trier les réclamations en fonction de leur score de sentiment
        complaints.sort(Comparator.comparingInt(Complaint::getScoreSentiment).reversed());
        return complaints; }

  /*  public List<Complaint> trierReclamationsAlgorithmeSentimments(List<Complaint> complaints){
        String[] positiveWords = {"good", "great", "excellent", "happy", "satisfied"};
        String[] negativeWords = {"problem", "bad", "unsatisfactory", "uncomfortable", "dirty", "disappointing"};
        String[] badWords = {"bitch", "ass", "asshole", "cunt", "dick", "shit", "fuck"};
        // Calculer le score de sentiment pour chaque réclamation
        for (Complaint complaint : complaints) {
            String description = complaint.getDescription();
            int scorePositif = 0;
            int scoreNegatif = 0;
            boolean hasBadWords = false;
            for (String word : positiveWords) {
                if (description.contains(word)) {
                    scorePositif++;
                }
            }
            for (String word : negativeWords) {
                if (description.contains(word)) {
                    scoreNegatif++;
                }
            }
            for (String badWord : badWords) {
                if (description.contains(badWord)) {
                    hasBadWords = true;
                    description = description.replaceAll(badWord, "****");
                }
            }
            complaint.setScoreSentiment(scorePositif - scoreNegatif);
            complaint.setDescription(description);
            if (hasBadWords) {
                int numberOfAlerts = complaint.getClient().getNumberOfAlerts();
                if (numberOfAlerts < 3) {
                    complaint.getClient().setNumberOfAlerts(numberOfAlerts + 1);
                    sendComplaintEmail(complaint.getClient().getEmail(), "Avertissement", "Bonjour " + complaint.getClient().getUsername() + ",\n\nNous avons détecté un contenu inapproprié dans votre réclamation avec l'identifiant " + complaint.getIdComplaint() + ". Nous vous informons que ce comportement n'est pas acceptable et peut entraîner la suspension ou la résiliation de votre compte s'il se poursuit. Veuillez vous assurer que toutes vos réclamations respectent nos normes en matière de contenu approprié.\n\nCordialement,\nService réclmations .");
                } else {
                    complaint.getClient().setIsBlocked(true);
                }
            }
        }
        // Trier les réclamations en fonction de leur score de sentiment
        complaints.sort(Comparator.comparingInt(Complaint::getScoreSentiment).reversed());
        return complaints;

    };*/



    @Scheduled(fixedDelay = 60000)
    public void TraiterClaimsForBadWords() {
        String[] badWords = {"bitch", "ass", "asshole", "cunt", "dick", "shit", "fuck"};
        List<Complaint> complaints = complaintRepo.findAll();
        for (Complaint complaint : complaints) {
            String description = complaint.getDescription();
            boolean hasBadWords = false;
            for (String badWord : badWords) {
                if (description.contains(badWord)) {
                    hasBadWords = true;
                    description = description.replace(badWord, "****");
                }
            }
            complaint.setDescription(description);
            if (hasBadWords) {
                int numberOfAlerts = complaint.getClient().getNumberOfAlerts();
                if (numberOfAlerts < 3) {
                    complaint.getClient().setNumberOfAlerts(numberOfAlerts + 1);
                    sendComplaintEmail(complaint.getClient().getEmail(), "avertissemnet", "Bonjour ," + complaint.getClient().getUsername() + ",Nous avons détecté un contenu inapproprié dans votre réclamation avec l'identifiant," + complaint.getIdComplaint() + " . Nous vous informons que ce comportement n'est pas acceptable et peut entraîner la suspension ou la résiliation de votre compte s'il se poursuit. Veuillez vous assurer que toutes vos réclamations respectent nos normes en matière de contenu approprié." );
                }  else {
                        complaint.getClient().setIsBlocked(true);
                }
                complaintRepo.save(complaint);
                iuser.save(complaint.getClient());
            }
        }}



    /*  public List<Complaint> trierReclamationsAlgorithmeSentimments(List<Complaint> complaints){
        String[] positiveWords = {"good", "great", "excellent", "happy", "satisfied"};
        String[] negativeWords = {"problem", "bad", "unsatisfactory", "uncomfortable", "dirty", "disappointing"};
        String[] badWords = {"bitch", "ass", "asshole", "cunt", "dick", "shit", "fuck"};
        // Calculer le score de sentiment pour chaque réclamation
        for (Complaint complaint : complaints) {
            String description = complaint.getDescription();
            int scorePositif = 0;
            int scoreNegatif = 0;
            boolean hasBadWords = false;
            for (String word : positiveWords) {
                if (description.contains(word)) {
                    scorePositif++;
                }
            }
            for (String word : negativeWords) {
                if (description.contains(word)) {
                    scoreNegatif++;
                }
            }
            for (String badWord : badWords) {
                if (description.contains(badWord)) {
                    hasBadWords = true;
                    description = description.replaceAll(badWord, "****");
                }
            }
            complaint.setScoreSentiment(scorePositif - scoreNegatif);
            complaint.setDescription(description);
            if (hasBadWords) {
                int numberOfAlerts = complaint.getClient().getNumberOfAlerts();
                if (numberOfAlerts < 3) {
                    complaint.getClient().setNumberOfAlerts(numberOfAlerts + 1);
                    sendComplaintEmail(complaint.getClient().getEmail(), "Avertissement", "Bonjour " + complaint.getClient().getUsername() + ",\n\nNous avons détecté un contenu inapproprié dans votre réclamation avec l'identifiant " + complaint.getIdComplaint() + ". Nous vous informons que ce comportement n'est pas acceptable et peut entraîner la suspension ou la résiliation de votre compte s'il se poursuit. Veuillez vous assurer que toutes vos réclamations respectent nos normes en matière de contenu approprié.\n\nCordialement,\nService réclmations .");
                } else {
                    complaint.getClient().setIsBlocked(true);
                }
            }
        }
        // Trier les réclamations en fonction de leur score de sentiment
        complaints.sort(Comparator.comparingInt(Complaint::getScoreSentiment).reversed());
        return complaints;

    };*/  }




