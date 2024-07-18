package project.UserService.infraestructure.outputs.mappers;


import org.mapstruct.Mapper;
import project.UserService.domain.models.RoleModel;
import project.UserService.infraestructure.outputs.entities.Role;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    RoleModel toRoleModel(Role role);
    Role toRole(RoleModel roleModel);
}
