package bufalari.com.contruction.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String tertiaryPhoneNumber;
    private String city;
    private String province;
    private String country;
}
