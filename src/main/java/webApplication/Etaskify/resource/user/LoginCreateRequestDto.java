package webApplication.Etaskify.resource.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webApplication.Etaskify.service.validation.ValidPassword;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

}
