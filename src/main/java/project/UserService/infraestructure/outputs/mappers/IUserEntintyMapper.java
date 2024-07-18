package project.UserService.infraestructure.outputs.mappers;

import org.mapstruct.Mapper;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.outputs.entities.User;


@Mapper(componentModel = "spring")
public interface IUserEntintyMapper {
    UserModel toUserModel(User user);
    User toUser(UserModel userModel);
}
