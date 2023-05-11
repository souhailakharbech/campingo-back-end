package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Activity;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;

import java.util.List;

public interface IPictureService
{
    List<Picture> retrieveAllPicture();

    Picture addPicture (Picture p);

    Picture updatePicture (Picture p);

    Picture retrievePicture(Integer idpicture);

    //Etudiant removeEtudiant(Long idEtudiant);

    void deletePicture(Integer idpicture);
}
