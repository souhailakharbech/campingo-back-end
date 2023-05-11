package webuild.esprit.tn.tunisiacampwebapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Invoice;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IInvoiceServices;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/Invoice")
public class InvoiceController {
    @Autowired
    IInvoiceServices iInvoiceServices;

    @GetMapping("/InvoiceList")
    public List<Invoice> ListInvoice(){
        return iInvoiceServices.retrieveAllInvoice();
    }

    @GetMapping("/genererInvoice")
    public ResponseEntity<String> genererFactures(){
        iInvoiceServices.genererFactures();
        return new ResponseEntity<>("Factures générées avec succès", HttpStatus.OK);
    }

    @GetMapping("/{commandeId}/pdf")
    public ResponseEntity<String> generatePdfInvoice(@PathVariable("commandeId") Integer commandeId){
        iInvoiceServices.createInvoice(commandeId);
        return ResponseEntity.ok().build();
    }
}
