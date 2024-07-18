package project.UserService.infraestructure.inputs.mapper;

import org.mapstruct.Mapper;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.CreateUserRequest;


@Mapper(componentModel = "spring",unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IUserModelMapper {
    UserModel toUserModel(CreateUserRequest request);
}
