package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CompanyDTO {

    @NotBlank(message = "Company name is required")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Primary phone number is required")
    private String primaryPhoneNumber;

    @NotBlank(message = "Secondary phone number is required")
    private String secondaryPhoneNumber;

    @NotBlank(message = "Tertiary phone number is required")
    private String tertiaryPhoneNumber;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "Country is required")
    private String country;
}
