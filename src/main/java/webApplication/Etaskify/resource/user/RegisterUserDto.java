package webApplication.Etaskify.resource.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webApplication.Etaskify.service.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @NotNull
    private String name;
    
    @Email
    @NotNull
    private String email;

    @ValidPassword
    private String password;

    private String phoneNumber;

    private Long organizationId;
}
