package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.Iuser;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserServices {
    @Autowired
    Iuser iuserRpo ;

    @Override
    public User addUser(User user) {
        return iuserRpo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return iuserRpo.findAll();
    }

    @Override
    public User findByIdUser(Integer idUser) {
        return iuserRpo.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void removeUserbyid(Integer idUser) {
        iuserRpo.deleteById(idUser);
    }

    @Override
    public User updateUser(Integer id, User user) {
        User u = iuserRpo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        u.setNameUser(user.getNameUser());
        u.setFamilyName(user.getFamilyName());
        u.setBirthday(user.getBirthday());
        u.setAdress(user.getAdress());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setIsBlocked(user.getIsBlocked());
        u.setUserType(user.getUserType());
        u.setIsActive(user.getIsActive());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setLoyaltyPoints(user.getLoyaltyPoints());

        return iuserRpo.save(u);
    }
}
