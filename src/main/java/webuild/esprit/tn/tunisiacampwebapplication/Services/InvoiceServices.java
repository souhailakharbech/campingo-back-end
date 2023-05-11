package webuild.esprit.tn.tunisiacampwebapplication.Services;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Commande;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.DetailBasket;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Invoice;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.CommandeRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.DetailBasketRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.InvoiceRepo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class InvoiceServices implements IInvoiceServices{
    @Autowired
    InvoiceRepo invoiceRepo;
    @Autowired
    CommandeRepo commandeRepo;
    @Autowired
    DetailBasketRepo detailBasketRepo;
    @Override
    public List<Invoice> retrieveAllInvoice() {
        return invoiceRepo.findAll();
    }

    @Override
    public void genererFactures() {
        List<Commande> commandes= commandeRepo.findAll();
        for (Commande commande : commandes){
            Invoice invoice= new Invoice();
            invoice.setReference(commande.getRef());
            invoice.setCreationDate(commande.getInvoice().getCreationDate());
            commandeRepo.save(commande);

        }
    }

    @Override
    public Invoice createInvoice(Integer idCommande) {
        Invoice invoice = new Invoice();
        invoice.setReference(invoice.getReference());
        invoice.setCreationDate(LocalDate.from(LocalDateTime.now()));
        invoice.setCommande(commandeRepo.getReferenceById(idCommande));
        invoiceRepo.save(invoice);
        try {
            generatePdf(idCommande);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return invoice;
    }


    @Override
    public void generatePdf(Integer commandeId) throws IOException {
        Invoice invoice = new Invoice();
        invoice.setReference(invoice.getReference());
        invoice.setCreationDate(LocalDate.from(LocalDateTime.now()));
        invoice.setCommande(commandeRepo.getReferenceById(commandeId));

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//        String path="src/main/assets/invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(pdfOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        Image logo = new Image(ImageDataFactory.create("src/main/resources/assets/camp.png"));
        logo.setWidth(100);
        logo.setHeight(50);

        logo.setFixedPosition(10, 800);

        document.add(logo);
        // Add a title to the PDF
        Paragraph title = new Paragraph("A purchase order   : " + invoice.getReference());
        title.setTextAlignment(TextAlignment.CENTER);
        document.add(title);
        // Add a description to the PDF
        Paragraph description = new Paragraph("The date that the order was placed was " + invoice.getCreationDate() +".");
        description.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(description);

        //Add commande to PDF
        Table table = new Table(new float[]{4,1,2});
        table.setWidth(UnitValue.createPercentValue(100));
        table.addCell(new Cell().add(new Paragraph("Product")));
        table.addCell(new Cell().add(new Paragraph("Quantity")));
        table.addCell(new Cell().add(new Paragraph("Price")));

        List<DetailBasket> detailBaskets = detailBasketRepo.findAll();
        for (DetailBasket detailBasket : detailBaskets){
            table.addCell(new Cell().add(new Paragraph("Number of pieces per item: " + detailBasket.getNbrperPiece())));
            table.addCell(new Cell().add(new Paragraph("Total number of pieces: " + detailBasket.getNbrPieceTotal())));
            table.addCell(new Cell().add(new Paragraph("Purchase Date: " + detailBasket.getPurchaseDate().format(DateTimeFormatter.ISO_DATE))));
            table.addCell(new Cell().add(new Paragraph("Commande Ref: " + detailBasket.getCommande().getRef())));
            table.addCell(new Cell().add(new Paragraph("Tools: " + detailBasket.getTools().toString())));
        }
        document.add(table);
        Paragraph Total = new Paragraph("Total:" + commandeRepo.getReferenceById(commandeId).getTotalAmount());
        log.error("le totale est :" + String.valueOf(commandeRepo.getReferenceById(commandeId).getTotalAmount()));
        document.add(Total);

        //close pdf
        document.close();
        byte[] pdfData= pdfOutputStream.toByteArray();

        //save pdf to file
        String fileName = invoice.getReference()+ ".pdf";
        String filePath = "src/main/resources/assets/cam.png"+ fileName;
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(pdfData);
        fos.flush();
        fos.close();
        invoiceRepo.save(invoice);
}
}
