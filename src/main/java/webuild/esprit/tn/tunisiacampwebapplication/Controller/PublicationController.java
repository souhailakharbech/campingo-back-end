package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.PostComments;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Publication;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PublicationRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Services.FileStorageService;
import webuild.esprit.tn.tunisiacampwebapplication.Services.PostCommentService;
import webuild.esprit.tn.tunisiacampwebapplication.Services.PublicationService;
import webuild.esprit.tn.tunisiacampwebapplication.api.FacebookService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/Publication")
public class PublicationController {
    @Autowired
    PublicationService pubs;
    @Autowired
    PublicationRepo pubR;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    FacebookService facebookService;

    @GetMapping("/retrieve-all-Publication")
    public List<Publication> getallPublication() {
        return  pubs.retrieveAllPublication();
    }

    @GetMapping("/retrievePublicationbydate")
    public List<Publication> retrievePublicationbydate() {
        return  pubs.retrievePublicationbydate();
    }



    @GetMapping("/retrievedate")
    public List<LocalDate> getdate() {
        return  pubs.getdate();
    }


    @GetMapping("/retrieve-Publication/{Publication-id}")
    public Publication retrievePublicationh(@PathVariable("Publication-id") Integer idPublication) {
        return pubs.retrievePublication(idPublication);}





    @PostMapping("/add-Publication")
    public ResponseEntity<String>  addPublication(@RequestParam("content")  String c,@RequestParam("location") String l, @RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
      //Publication e = new Publication();
      //e.setContent(c);

        String fileName = file.getOriginalFilename();
        String filePath = fileStorageService.storeFile(file, fileName);
        //return e;
        //return  userId.intValue();

       return  pubs.PostPublication(c,l,filePath,id);
    }
    @PostMapping("/update-Publication")
    public ResponseEntity<String>  updatePublication(@RequestParam("idpub") Integer ID,@RequestParam("file") MultipartFile file,@RequestParam("content")  String c) throws IOException {
      Publication P = pubR.findById(ID).get();
      P.setContent(c);
      P.setDatepublication(new Date());
      //  String fileName = file.getOriginalFilename();
       // String filePath = fileStorageService.updatestoreFile(file, fileName);
        //return e;
        //return  userId.intValue();

        return  pubs.updatePublication(P,file);
    }

    @PostMapping("/add-Publication1")
    public boolean   addPublication1 (@RequestBody Publication e) {

        return  pubs.testreturn(e);
    }




    @DeleteMapping("/remove-Publication/{Publication-id}")
    public void removePublication(@PathVariable("Publication-id") Long idPublication) {
        pubs.removeidPost(Math.toIntExact(idPublication));
    }


   /* @PutMapping("/update-Publication")
    public ResponseEntity<String> updatePublication(@RequestBody Publication e) {

        return  pubs.PostPublication(e,);
    }*/

  /*  @GetMapping("/getbyhashtag/{hashtag}")
    public List<Publication> getbyhashtag(@PathVariable("hashtag") String hashtag) {
        return pubs.getbyhashtag(hashtag);
    }
   /* @GetMapping("/getbyhashtag2/{hashtag}")
    public List<Publication> getbyhashtag2(@PathVariable("hashtag") String hashtag) {
        return pubs.getbyhashtag(hashtag);

    }*/
   @GetMapping("/hashtag")
    public List<Publication>  pub(@RequestParam("hashtag")  String hashtag) {
        return pubs.getbyhashtag(hashtag); }

//Ctrl+Shift+R TO REPLACE
  /*@GetMapping("/hashtag")
  public String pub(@RequestParam("hashtag")  String hashtag) {
      return pubs.getbyhashtag(hashtag); }*/




    @PostMapping("/post-to-facebook")
    public void postToFacebook(@RequestBody Publication postRequest) {

    }







}