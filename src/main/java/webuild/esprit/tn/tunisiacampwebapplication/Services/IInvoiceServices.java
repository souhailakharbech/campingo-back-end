package webuild.esprit.tn.tunisiacampwebapplication.Services;

import webuild.esprit.tn.tunisiacampwebapplication.Entities.Invoice;

import java.io.IOException;
import java.util.List;

public interface IInvoiceServices {
    List<Invoice> retrieveAllInvoice();
    public void genererFactures();
    public Invoice createInvoice (Integer idCommande);
    public void generatePdf(Integer commandeId) throws IOException;

}
