package project.UserService.application.services;

import org.springframework.stereotype.Service;
import project.UserService.application.ports.inputs.IUserServicePorts;
import project.UserService.application.ports.outputs.IUserPersistencePort;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserServicePorts {

    private final IUserPersistencePort persistencePort;

    public UserServiceImpl(IUserPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public List<UserModel> list() {
        return persistencePort.list();
    }

    @Override
    public UserModel findByEmail(String email) {
        return persistencePort.findByEmail(email);
    }

    @Override
    public UserModel findById(Long id) {
        return persistencePort.findById(id);
    }

    @Override
    public UserModel create(UserModel user) {
        return persistencePort.create(user);
    }

    @Override
    public UserModel update(Long id, UpdateUserRequest user) {
        return persistencePort.update(id, user);
    }

    @Override
    public void delete(Long id) {
        persistencePort.delete(id);
    }
}
