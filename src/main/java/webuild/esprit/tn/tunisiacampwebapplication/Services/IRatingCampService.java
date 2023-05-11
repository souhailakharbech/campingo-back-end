package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.RatingCamp;

public interface IRatingCampService {

    Double addRating(RatingCamp rating, Integer IdCampsites, Integer idUser);
}
