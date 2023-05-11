package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CommandeRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Services.ICommandeServices;

import java.util.List;

@RestController
@RequestMapping("/Commande")
public class CommandeController {
    @Autowired
    ICommandeServices commandeServices;
    @Autowired
    CommandeRepo commandeRepo;
    @GetMapping("/DetailBasketList")
    public List<Commande> ListCommande(){
        return commandeServices.retrieveAllCommande();
    }
    @PostMapping("/addCommande")
    @ResponseBody
    public void addCommande(@RequestBody Commande c){
        commandeServices.addCommande(c);
    }
    @DeleteMapping("/DeleteCommande/{idCommande}")
    public void deleteCommande(@RequestBody Commande c,@PathVariable Integer idCommande){
        commandeServices.deleteCommande(idCommande);
    }
}
