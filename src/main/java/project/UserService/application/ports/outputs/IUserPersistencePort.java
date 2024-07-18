package project.UserService.application.ports.outputs;

import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IUserPersistencePort {
    List<UserModel> list();
    UserModel findByEmail(String email);
    UserModel findById(Long id);
    UserModel create(UserModel user);
    UserModel update(Long id, UpdateUserRequest user);
    void delete(Long id);
}
