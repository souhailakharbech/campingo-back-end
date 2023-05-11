package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.DetailBasket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;

import java.util.List;
import java.util.Map;

public interface IDetailBasketServices {
    List<DetailBasket> retrieveAllDetailBasket();
    DetailBasket addDetailBasket (DetailBasket db);
    DetailBasket updateDetailBasket (DetailBasket db);
    DetailBasket retrieveDetailBasket (Integer idDetail);
    void deleteDetailBasket (Integer idDetail);

    public DetailBasket addDetailBasketToBasket(Integer idDetailBasket, Integer idBasket);
    public DetailBasket addDetailBasketToCommande(Integer idDetailBasket, Integer idCommande);
    public DetailBasket addAndAssignDetailsToToolsAndCommande(DetailBasket db,Integer idTools, Integer idCommande);
    public Map<String, Object> calculateQuantityAndTotalPrice(List<Tools> tools, List<DetailBasket> detailBaskets);
}
