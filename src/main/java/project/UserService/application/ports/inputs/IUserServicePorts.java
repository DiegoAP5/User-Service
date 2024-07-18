package project.UserService.application.ports.inputs;

import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IUserServicePorts {
    List<UserModel> list();
    UserModel findByEmail(String email);
    UserModel findById(Long id);
    UserModel create(UserModel user);
    UserModel update(Long id, UpdateUserRequest user);
    void delete(Long id);
}
