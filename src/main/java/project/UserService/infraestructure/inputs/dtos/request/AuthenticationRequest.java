package project.UserService.infraestructure.inputs.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthenticationRequest{

    private String email;

    private String password;
}
