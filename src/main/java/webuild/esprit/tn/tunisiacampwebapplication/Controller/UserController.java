package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.User;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IUserServices;

import javax.validation.Valid;
import java.util.List;
@RestController
@AllArgsConstructor


@RequestMapping("/AUTH/auth")

public class UserController {
    @Autowired
    IUserServices iUserServices ;

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return iUserServices.addUser(user);
    }

    @GetMapping("/gettAllUsers")
    public List<User> getAllUsers() {
        return iUserServices.getAllUsers();
    }


     @GetMapping("/FindbyIdUser/{id}")
    public User findByIdUser(@PathVariable(value = "id")Integer idUser){
       return iUserServices.findByIdUser(idUser) ;
    }

    @PutMapping("/UserUpdate/{id}")
    public User updateUser(@PathVariable(value = "id")Integer id,@Valid @RequestBody User user) {
        return iUserServices.updateUser(id, user);
    }

    @DeleteMapping("/UserDelete/{id}")
    public void removeUserbyid(@PathVariable(value = "id")Integer idUser) {
        iUserServices.removeUserbyid(idUser);
    }
}
