package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.*;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PictureRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PostCommentsRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PublicationRepo;
import webuild.esprit.tn.tunisiacampwebapplication.api.SendMailService;


import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class PublicationService {
    @Autowired
    PublicationRepo pubR;
    @Autowired
    Iuser userR;
    @Autowired
    PictureRepo pr;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    PostCommentsRepo cmtr;
    @Autowired
    PostCommentService cmtRS;
    @Autowired
    SendMailService mail;
    //the test path the original url

    String theoriginalpath = "C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/20230304160340_";
    Integer originalparhlenth = theoriginalpath.length();

    public List<LocalDate> getdate() {
        List<Publication> all =pubR.findAll();
        List<LocalDate> finale =new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate lastWeekDate = currentDate.minusDays(7);
     /*   for (Publication p :all){
            Date creationDate = p.getDatepublication();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss", Locale.FRENCH);
            LocalDate postCreationDate = LocalDate.parse(creationDate.toLocaleString(), formatter);
            finale.add(postCreationDate);


        }*/
        finale.add(lastWeekDate);


        return finale;
    }
    public List<Publication> retrieveAllPublication() {
        return pubR.findAll() ;
    }

//list of pub the last 7 days
   public List<Publication> retrievePublicationbydate() {
       List<Publication> all =pubR.findAll();
       List<Publication> finale =new ArrayList<>();
       LocalDate currentDate = LocalDate.now();
       LocalDate lastWeekDate = currentDate.minusDays(7);
       for (Publication p :all){
           Date creationDate = p.getDatepublication();
           if (creationDate != null) {
             //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss");
             //  LocalDate postCreationDate = LocalDate.parse(creationDate.toLocaleString(), formatter);




               LocalDate postCreationDate = Instant.ofEpochMilli(creationDate.getTime())
                       .atZone(ZoneId.systemDefault())
                       .toLocalDate();

               if (postCreationDate.isAfter(lastWeekDate)) {
                   finale.add(p);
               }
               if (postCreationDate.isBefore(lastWeekDate)) {
                   p.setIsarchived(true);
                   pubR.save(p);
               }
           }

       }


       return finale;
   }











    public ResponseEntity<String> PostPublication(String c , String l,String path,Integer iduser)  {
        Publication w =new Publication();
       //  mail.sendmail("takwalassoued066@gmail.com","subj","content");
        w.setContent(c);
        w.setLocation(l);
        w.setDatepublication(new Date());
        User u = userR.findById(iduser).get();
        w.setUser(u);

        if (searchforbadwords(c)== true) {
            Picture p = pr.findByUrl(path).get();
            pr.delete(p);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your post contain inappropriate words ");

        }else{


            if(path.toString().length()>originalparhlenth){
            Picture pic = pr.findByUrl(path).get();
            w.setPictures(pic);
            pubR.save(w);

           return ResponseEntity.status(HttpStatus.OK).body("post aded");
            }else  { pubR.save(w);return ResponseEntity.status(HttpStatus.OK).body("post aded without pic");}

       }


    }
//updating the pub
    public ResponseEntity<String> updatePublication(Publication w , MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();


//new path ******
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueFileName = timestamp + "_" + fileName;
        Path filePath = Paths.
                get("C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/" +
                        uniqueFileName);
        Picture p = w.getPictures();
        if (searchforbadwords(w.getContent())== true) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your post contain inappropriate words ");

        }else{
   try {
       //// if there is already pic in database
       if (p!=null) {
           // if the user uploaded new pic to replace the old one
           if (filePath.toString().length() > originalparhlenth) {
               File fileToDelete = new File(p.getUrl());
               fileToDelete.delete();
               p.setUrl(filePath.toString());
               Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
               pr.save(p);
               pubR.save(w);

               //  return filePath.toString();
           } else {
             //if the user didn't upload new picture he will keep the old one
               pubR.save(w);
           }
       }else {
           pubR.save(w);
       }

       } catch(IOException e){
           throw new RuntimeException("Failed to store file " + fileName, e);
       }}
         return ResponseEntity.status(HttpStatus.OK).body("post updated !");
    }















    public Boolean testreturn (Publication w) {
       return searchforbadwords(w.getContent());


    }


    public Publication retrievePublication(Integer idPublication) {
        return pubR.findById(idPublication).get();
    }
//remove the post and it comments
    public void removeidPost(Integer idPublication) {
        Publication pub = pubR.findById(idPublication).get();
        Picture p = pr.findById(pub.getPictures().getIdpicture()).get();
        File fileToDelete = new File(p.getUrl());
        fileToDelete.delete();
        pr.delete(p);
        Set<PostComments> pc = pub.getPostComments();
        for (PostComments cm :pc) {
            PostComments postc = cmtr.findById(cm.getIdcmtr()).get();
            cmtRS.removeidPostComments(postc.getIdcmtr());
        }
        pubR.deleteById(idPublication);
    }

    //algorithm searching for bad words
    public boolean searchforbadwords(String text) {
        List<String> badWords = Arrays.asList("W1", "W2", "W3");
        String[] words = text.split(" ");
        boolean result = false;
        badWords = badWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        for (String word : words) {
            int test = 0;
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            if (badWords.contains(word.toLowerCase())) {
                test += 1;
            }
            if (test > 0) {
                result = true;
                break;
            }
        }

        return result;
    }

    // algo searching for post by hashtag

    public   List<Publication>  getbyhashtag(String listofhashtags) {
        String[] hashtags = listofhashtags.split(" ");
        List<Publication> pubwithhashtag=new ArrayList<>();
        List<Publication> allpub = pubR.findAll();
       for (String hashtag : hashtags){
            for (Publication  p : allpub){
               if (p.getContent().contains(hashtag)==true){
                    pubwithhashtag.add(p);
                }
            }
        }
      Comparator  <Publication> reactionCountComparator = Comparator.comparingInt(p -> p.getReactions().size());

        List<Publication> sortedPublications = pubwithhashtag.stream()
                .sorted(reactionCountComparator.reversed())
                .collect(Collectors.toList());
        return sortedPublications ;
       // return pubwithhashtag;
    }

}


