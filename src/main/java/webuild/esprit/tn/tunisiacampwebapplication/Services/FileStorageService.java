package webuild.esprit.tn.tunisiacampwebapplication.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.Picture;
import webuild.esprit.tn.tunisiacampwebapplication.Repositories.PictureRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {
    @Autowired
    PictureRepo pr;
    public String storeFile(MultipartFile file, String fileName) {
        // Define the location where the file will be stored
      //  Path filePath = Paths.get("C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/" + fileName);

        // Check if the file already exists

            // If the file exists, add a timestamp to the filename to make it unique
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String uniqueFileName = timestamp + "_" + fileName;
            Path filePath = Paths.
                get("C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/" +
                        uniqueFileName);


        try {
                // Save the file to the specified location



                String theoriginalpath2 = "C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/20230304164700_";
                Integer originalparhlenth2 = theoriginalpath2.length();
               //ifa user aded a picture in his pub the url will be longer then the originalpathlenth2 so a pic object will be created and adeded in datatbase and the affectation wuill be in publication service
                if(filePath.toString().length()>originalparhlenth2) {
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    Picture p = new Picture();
                    p.setUrl(filePath.toString());
                    pr.save(p);
                    return filePath.toString();
                }else
                {
                    // the url will be returned without saving the pic because no picture is selected
                    return filePath.toString();
                }

              } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + fileName, e);
            }

        }
    public String updatestoreFile(MultipartFile file, String fileName) {
        // Define the location where the file will be stored
        //  Path filePath = Paths.get("C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/" + fileName);

        // Check if the file already exists

        // If the file exists, add a timestamp to the filename to make it unique
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uniqueFileName = timestamp + "_" + fileName;
        Path filePath = Paths.get("C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/" + uniqueFileName);


        try {
            // Save the file to the specified location



            String theoriginalpath2 = "C:/Users/lenovo/Desktop/test2/Campingo_web_application/src/main/java/webuild/esprit/tn/tunisiacampwebapplication/pictures/20230304164700_";
            Integer originalparhlenth2 = theoriginalpath2.length();

            if(filePath.toString().length()>originalparhlenth2) {
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return filePath.toString();
            }else
            {
                return filePath.toString();
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }

    }


    public static boolean hasSubstringAfterPictures(String str) {
        int index = str.indexOf("pictures/");
        return index != -1 && index < str.length() - "pictures/".length();
    }


//get file name from url string

 /*   public String extractFilenameFromStringUrl(String stringUrl) {
        File file = new File(stringUrl);
        String filename = file.getName();
        return filename;
    }*/
}