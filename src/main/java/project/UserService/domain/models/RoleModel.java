package project.UserService.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RoleModel {
    private Long id;
    private String role;
}
