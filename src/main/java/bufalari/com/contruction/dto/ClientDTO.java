package bufalari.com.contruction.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String province;
    private String postalCode;
    private String sinNumber;
}
