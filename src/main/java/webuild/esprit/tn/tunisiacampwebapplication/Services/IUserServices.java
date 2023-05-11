package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;

import java.util.List;

public interface IUserServices {
    User addUser (User user);
    public List<User> getAllUsers() ;
    public User findByIdUser(Integer idUser);
    public void removeUserbyid(Integer idUser);

    public User updateUser(Integer id, User user) ;
}
