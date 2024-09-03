package bufalari.com.contruction.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private String name;
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String tertiaryPhoneNumber;
    private String city;
    private String province;
    private String country;
}
