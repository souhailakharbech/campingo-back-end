package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IPictureService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AUTH/auth/picture")
public class PictureController
{
    IPictureService iPictureService;

    // http://localhost:8089/Campi/picture/retrieveAllPicture
    @GetMapping("/retrieveAllPicture")
    public List<Picture> retrieveAllPicture() {
        List<Picture> listPicture = iPictureService.retrieveAllPicture();
        return listPicture;
    }


    //  http://localhost:8089/Campi/picture/retrievePicture/1
    @GetMapping("/retrievePicture/{idpicture}")
    public Picture retrievePicture(@PathVariable("idpicture") Integer idpicture) {
        return iPictureService.retrievePicture(idpicture);
    }

    // http://localhost:8089/Campi/picture/addPicture
    @PostMapping("/addPicture")
    public Picture addPicture(@RequestBody Picture p) {
        Picture picture = iPictureService.addPicture(p);
        return picture;
    }

    // http://localhost:8089/Campi/picture/deletePicture/1
    @DeleteMapping("/deletePicture/{idpicture}")
    public void deletePicture(@PathVariable("idpicture") Integer idpicture) {
        iPictureService.deletePicture(idpicture);
    }

    // http://localhost:8089/Campi/picture/updatePicture
    @PutMapping("/updatePicture")
    public Picture updatePicture(@RequestBody Picture p) {
        Picture picture= iPictureService.updatePicture(p);
        return picture;
    }
}
