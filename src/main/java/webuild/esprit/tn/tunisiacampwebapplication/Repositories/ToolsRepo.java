package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;

import javax.persistence.PersistenceContext;
import java.util.List;

public interface ToolsRepo extends JpaRepository<Tools, Integer> {

    List<Tools> findAllByPriceperUnitIsBetween(float a, float b);
}
