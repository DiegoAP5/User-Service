package project.UserService.infraestructure.inputs.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    private String name;

    private String password;

    private String cellphone;

    private String email;

    private Long role_id;
}
