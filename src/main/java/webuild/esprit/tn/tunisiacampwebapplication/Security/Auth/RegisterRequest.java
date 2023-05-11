package webuild.esprit.tn.tunisiacampwebapplication.Security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webuild.esprit.tn.tunisiacampwebapplication.Entities.UserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
   private String nameUser  ;
    private String familyName  ;
    private Date birthday ;
    private String adress ;
    private String email  ;
    private String password  ;
   @Enumerated(EnumType.STRING)
   private UserType userType;
    //private UserType userType =UserType.CLIENT ;
    //private UserType userType1 =UserType.OWNER ;
  //  private UserType userType2 =UserType.DELIVERYPERSON ;
    private Integer phoneNumber;

}
