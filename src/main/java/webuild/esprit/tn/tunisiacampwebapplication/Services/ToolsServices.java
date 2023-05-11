package webuild.esprit.tn.tunisiacampwebapplication.Services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.DetailBasket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.DetailBasketRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ToolsRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Slf4j
public class ToolsServices implements IToolsServices {
    @Autowired
    ToolsRepo toolsRepo;
    @Autowired
    DetailBasketRepo detailBasketRepo;

    @Override
    public List<Tools> retrieveAllTools() {
        return toolsRepo.findAll();
    }

    @Override
    public Tools addTools(Tools t) {
        return toolsRepo.save(t);
    }

    @Override
    public Tools updateTools(Tools t) {
        return toolsRepo.save(t);
    }

    @Override
    public Tools retrieveTools(Integer idTools) {
        return toolsRepo.findById((int) idTools.longValue()).get();
    }

    @Override
    public void deleteTools(Integer idTools) {
        toolsRepo.deleteById(idTools);
    }

    @Override
    public Optional<Tools> findToolsById(Integer idTools) {
        return toolsRepo.findById(idTools);
    }

    @Override
    public Tools addToolsToDetailBasket(Integer idTools, Integer idDetailBasket) {
        Tools tools = toolsRepo.findById(idTools).orElse(null);
        DetailBasket detailBasket = detailBasketRepo.findById(idDetailBasket).orElse(null);
        tools.setDetailBasket(detailBasket);
        return toolsRepo.save(tools);
    }

    @Override
    public Map<String, Object> calculateQuantityAndTotalPrice(List<Tools> tools) {
        int totalQuantity = 0;
        float totalPrice = 0.0f;
        for (Tools tool : tools){
            totalQuantity += tool.getQuantity();
            totalPrice += tool.getQuantity()*tool.getPriceperUnit()*(1- tool.getPromotion()/100.0f);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("totalQuantity",totalQuantity);
        result.put("totalPrice", totalPrice);
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Tools> searchToolsByTypeAndBrand(String type, String brand) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tools> cq = cb.createQuery(Tools.class);
        Root<Tools> root = cq.from(Tools.class);
        List<Predicate> predicates = new ArrayList<>();
        if (type != null && !type.isEmpty()){
            predicates.add(cb.equal(root.get("Type"), type));
        }
        if (brand != null && !brand.isEmpty()) {
            predicates.add(cb.equal(root.get("Brand"), brand));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();

    }

    @Override
    public List<Tools> filterByPriceRange(float minPrice, float maxPrice) {
        /*List<Tools> filteredTools = new ArrayList<>();
        for (Tools tool : retrieveAllTools()){
            float price = tool.getPriceperUnit();
            if (price >= minPrice && price <= maxPrice){
                filteredTools.add(tool);
            }
        }
        return filteredTools;*/
        return toolsRepo.findAllByPriceperUnitIsBetween(minPrice, maxPrice);
    }
}
