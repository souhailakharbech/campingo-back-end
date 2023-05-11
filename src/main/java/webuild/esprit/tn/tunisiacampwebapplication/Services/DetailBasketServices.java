package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Basket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.DetailBasket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.BasketRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CommandeRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.DetailBasketRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ToolsRepo;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class DetailBasketServices implements IDetailBasketServices{
    @Autowired
    private ToolsRepo toolsRepo;
    @Autowired
    DetailBasketRepo detailBasketRepo;
    @Autowired
    BasketRepo basketRepo;
    @Autowired
    CommandeRepo commandeRepo;
    @Override
    public List<DetailBasket> retrieveAllDetailBasket() {

        return detailBasketRepo.findAll();
    }

    @Override
    public DetailBasket addDetailBasket(DetailBasket db) {
        return detailBasketRepo.save(db);
    }

    @Override
    public DetailBasket updateDetailBasket(DetailBasket db) {
        return detailBasketRepo.save(db);
    }

    @Override
    public DetailBasket retrieveDetailBasket(Integer idDetail) {
        return null;
    }

    @Override
    public void deleteDetailBasket(Integer idDetail) {
        detailBasketRepo.deleteById(idDetail);
    }

    @Override
    public DetailBasket addDetailBasketToBasket(Integer idDetailBasket, Integer idBasket) {
        DetailBasket detailBasket = detailBasketRepo.findById(idDetailBasket).orElse(null);
        Basket basket = basketRepo.findById(idBasket).orElse(null);
        detailBasket.setBasket(basket);
        return detailBasketRepo.save(detailBasket);

    }

    @Override
    public DetailBasket addDetailBasketToCommande(Integer idDetailBasket, Integer idCommande) {
        DetailBasket detailBasket = detailBasketRepo.findById(idDetailBasket).orElse(null);
        Commande commande = commandeRepo.findById(idCommande).orElse(null);
        detailBasket.setCommande(commande);
        return detailBasketRepo.save(detailBasket);
    }

    @Override
    @Transactional
    public DetailBasket addAndAssignDetailsToToolsAndCommande(DetailBasket db, Integer idTools, Integer idCommande) {
        Basket basket = basketRepo.findById(db.getBasket().getIdBasket()).orElse(null);
        db.setBasket(basket);
        detailBasketRepo.save(db);
        Tools tools = toolsRepo.findById(idTools).orElse(null);
        Commande commande = commandeRepo.findById(idCommande).orElse(null);
        tools.setDetailBasket(db);
        Set<Commande> commandes=new HashSet<>();
        commandes.add(commande);
        db.setCommande((Commande) commandes);
        return db;
    }

    @Override
    public Map<String, Object> calculateQuantityAndTotalPrice(List<Tools> tools, List<DetailBasket> detailBaskets) {
        int totalQuantity = 0;
        float totalPrice = 0.0f;
        for (Tools tool : tools){
            for (DetailBasket detailBasket: detailBaskets){
                totalQuantity += detailBasket.getNbrPieceTotal();
                totalPrice += detailBasket.getNbrPieceTotal()*tool.getPriceperUnit()*(1- tool.getPromotion()/100.0f);
            }

        }
        Map<String, Object> result = new HashMap<>();
        result.put("totalQuantity",totalQuantity);
        result.put("totalPrice", totalPrice);
        return result;
    }


}
