package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CommandeRepo;

import java.util.List;

@Service
@Slf4j
public class CommandeServices implements ICommandeServices{
    @Autowired
    CommandeRepo commandeRepo;
    @Override
    public List<Commande> retrieveAllCommande() {
        return commandeRepo.findAll();
    }

    @Override
    public Commande addCommande(Commande c) {
        return commandeRepo.save(c);
    }

    @Override
    public Commande retrieveCommande(Integer idCommande) {
        return commandeRepo.findById((int) idCommande.longValue()).get();
    }

    @Override
    public void deleteCommande(Integer idCommande) {
        commandeRepo.deleteById(idCommande);

    }
}
