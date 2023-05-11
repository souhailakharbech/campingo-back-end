package webuild.esprit.tn.tunisiacampwebapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Basket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
}
