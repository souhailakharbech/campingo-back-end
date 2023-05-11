package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PictureRepo;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PictureService implements IPictureService
{
    PictureRepo pictureRepo;

    @Override
    public List<Picture> retrieveAllPicture() {

        return pictureRepo.findAll();
    }

    @Override
    public Picture addPicture(Picture p)
    {
        return pictureRepo.save(p);
    }

    @Override
    public Picture updatePicture(Picture p) {

        return pictureRepo.save(p);
    }

    @Override
    public Picture retrievePicture(Integer idpicture)
    {
        return pictureRepo.findById(idpicture).get();
    }

    @Override
    public void deletePicture(Integer idpicture)
    {
                 pictureRepo.deleteById(idpicture);
    }
}
