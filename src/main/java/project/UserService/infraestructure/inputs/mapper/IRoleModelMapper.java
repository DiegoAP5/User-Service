package project.UserService.infraestructure.inputs.mapper;

import org.mapstruct.Mapper;
import project.UserService.domain.models.RoleModel;
import project.UserService.infraestructure.inputs.dtos.request.CreateRoleRequest;

@Mapper(componentModel = "spring",unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)

public interface IRoleModelMapper {
    RoleModel toRoleModel(CreateRoleRequest role);
}


