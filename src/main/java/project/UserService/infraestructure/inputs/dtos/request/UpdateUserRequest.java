package project.UserService.infraestructure.inputs.dtos.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {

    private String name;

    private String password;

    private String image;

    private Long role_id;
}
