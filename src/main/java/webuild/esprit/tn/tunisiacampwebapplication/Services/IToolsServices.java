package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IToolsServices {
    List<Tools> retrieveAllTools();
    Tools addTools(Tools t);
    Tools updateTools(Tools t);
    Tools retrieveTools (Integer idTools);
    void deleteTools(Integer idTools);
    public Optional<Tools> findToolsById(Integer idTools);

    public Tools addToolsToDetailBasket(Integer idTools, Integer idDetailBasket);
    public Map<String, Object> calculateQuantityAndTotalPrice(List<Tools> tools);
    public List<Tools> searchToolsByTypeAndBrand(String type, String brand);
    public List<Tools> filterByPriceRange(float minPrice, float maxPrice);
}
