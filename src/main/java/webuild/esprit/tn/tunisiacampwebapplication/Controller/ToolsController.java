package webuild.esprit.tn.tunisiacampwebapplication.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.ImageModel;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Tools;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.ToolsRepo;
import webuild.esprit.tn.tunisiacampwebapplication.Services.IToolsServices;

import java.io.IOException;
import java.util.*;

@CrossOrigin("*")

@RestController
@RequestMapping("/Tools")
public class ToolsController {
    @Autowired
    IToolsServices toolsServices;
    @Autowired
    ToolsRepo toolsRepo;

    @PostMapping(value = {"/addTools"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Tools addTools(@RequestPart("tools") Tools tools,
                        @RequestPart("imageFile") MultipartFile[] file)  {

        try {
            Set<ImageModel> images = uploadImage(file);
            tools.setToolsImages(images);
            return toolsServices.addTools(tools);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null ;
        }

    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles)  throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for(MultipartFile file : multipartFiles){
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels ;
    }
//    public Tools addTools(@RequestPart("tools") Tools tools,
//                         @RequestPart("imageFile") MultipartFile[] multipartFiles){
//
//        toolsServices.addTools(tools);
//        try {
//            Set<ImageModel> images = new HashSet<>();
//            for (MultipartFile file: multipartFiles){
//                images.addAll(uploadImage(file));
//            }
//            tools.setToolsImages(images);
//            return toolsServices.addTools(tools);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }

//    public Set<ImageModel> uploadImage( MultipartFile file) throws IOException {
//        Set<ImageModel> imageModels = new HashSet<>();
//
//            ImageModel imageModel = new ImageModel(
//                    file.getOriginalFilename(),
//                    file.getContentType(),
//                    file.getBytes()
//            );
//            imageModels.add(imageModel);
//
//
//        return imageModels;
//    }
    @CrossOrigin(origins = "http://localhost:4200")


    @GetMapping("/ToolsList")
    public List<Tools> ListTools(){
        return toolsServices.retrieveAllTools();
    }

    @GetMapping("/Tools/{idTools}")
    public Optional<Tools> GetToolsbyId(@PathVariable ("idTools") Integer idTools){
        return toolsServices.findToolsById(idTools);
    }
    @PutMapping("/UpdateTools")
    public Tools updateTools(@RequestBody Tools t){
       Tools tools=toolsServices.updateTools(t);
       return tools;
    }

//    @PutMapping("/UpdateTools/{idTools}")
//    public Tools updateTools(@RequestBody Tools t,@PathVariable Integer idTools){
//        return this.toolsRepo.findById(idTools).map(y->{
//            y.setType(t.getType());
//            y.setBrand(t.getBrand());
//            y.setPriceperUnit(t.getPriceperUnit());
//            return toolsRepo.save(y);
//        }).orElseGet(()->{
//            t.setIdTools(idTools);
//            return toolsRepo.save(t);
//        });
//    }

    @DeleteMapping("/DeleteTools/{idTools}")
    public void deleteTools(@PathVariable Integer idTools){
        toolsServices.deleteTools(idTools);
    }

    @PutMapping("/addToolsToDetail/{idtools}/{idDet}")
    public Tools addToolsToDetailBasket(@PathVariable ("idtools") Integer idTools,@PathVariable("idDet") Integer idDetailBasket){
        return toolsServices.addToolsToDetailBasket(idTools, idDetailBasket);

    }

    @GetMapping("/quantity-and-price")
    public Map<String, Object> getQuantityAndTotalPrice(){
        List<Tools> tools = new ArrayList<>();
        return toolsServices.calculateQuantityAndTotalPrice(tools);
    }

    @GetMapping("/filter")
    public List<Tools> searchToolsByTypeAndBrand(@RequestParam(required = false) String type,@RequestParam(required = false) String brand){
        return toolsServices.searchToolsByTypeAndBrand(type, brand);
    }

    @GetMapping("/filterByPriceRange")
    public List<Tools> filterByPriceRange(@RequestParam (name = "minPrice") float minPrice, @RequestParam (name = "maxPrice") float maxPrice){
        return toolsServices.filterByPriceRange(minPrice, maxPrice);
    }

}
