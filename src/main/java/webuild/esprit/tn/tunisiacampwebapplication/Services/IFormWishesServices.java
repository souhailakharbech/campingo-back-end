package webuild.esprit.tn.tunisiacampwebapplication.Services;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.FormWishes;

import java.util.List;

public interface IFormWishesServices
{
    List<FormWishes> retrieveAllFormWishes();

    void addFormWishesAndSendToOwners (FormWishes f,Integer userId);

    FormWishes updateFormWishes (FormWishes a);

    FormWishes retrieveFormWishes(Integer idFormWishes);

    void deleteFormWishes(Integer idFormWishes);

}
