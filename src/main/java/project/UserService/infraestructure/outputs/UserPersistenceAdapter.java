package project.UserService.infraestructure.outputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.UserService.application.ports.outputs.IRolePersistencePorts;
import project.UserService.application.ports.outputs.IUserPersistencePort;
import project.UserService.domain.models.RoleModel;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.UpdateUserRequest;
import project.UserService.infraestructure.outputs.entities.Role;
import project.UserService.infraestructure.outputs.entities.User;
import project.UserService.infraestructure.outputs.mappers.IRoleEntityMapper;
import project.UserService.infraestructure.outputs.mappers.IUserEntintyMapper;
import project.UserService.infraestructure.outputs.repositories.IRoleRepository;
import project.UserService.infraestructure.outputs.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserPersistenceAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntintyMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserPersistenceAdapter(IUserRepository userRepository, IUserEntintyMapper mapper, IRoleRepository repository) {
        this.userRepository = userRepository;
        this.roleRepository = repository;
        this.mapper = mapper;
    }
    @Override
    public List<UserModel> list() {
        return userRepository.findAll().stream().map(mapper::toUserModel).toList();
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserModel findById(Long id) {
        return userRepository.findById(id).map(mapper::toUserModel).orElse(null);
    }

    @Override
    public UserModel create(UserModel user) {
        User user1 = mapper.toUser(user);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user1);

        return mapper.toUserModel(newUser);
    }

    @Override
    public UserModel update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (request.getPassword() != null) {
                user.setPassword(request.getPassword());
            }
            if (request.getImage() != null) {
                user.setImage(request.getImage());
            }
            if (request.getName() != null) {
                user.setName(request.getName());
            }
            if (request.getRole_id() != null) {
                Role role = roleRepository.findRoleById(request.getRole_id());
                user.setRole(role);
            }
            User updater = userRepository.save(user);
            return mapper.toUserModel(updater);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
