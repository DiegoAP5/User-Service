package project.UserService.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UserModel {
    private Long id;
    private String name;
    private String image;
    private String email;
    private String cellphone;
    private String password;
    private Long role_id;
}
