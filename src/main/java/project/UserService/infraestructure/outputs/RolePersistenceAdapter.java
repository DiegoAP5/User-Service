package project.UserService.infraestructure.outputs;


import org.springframework.stereotype.Component;
import project.UserService.application.ports.outputs.IRolePersistencePorts;
import project.UserService.domain.models.RoleModel;
import project.UserService.infraestructure.outputs.entities.Role;
import project.UserService.infraestructure.outputs.mappers.IRoleEntityMapper;
import project.UserService.infraestructure.outputs.repositories.IRoleRepository;

import java.util.List;

@Component
public class RolePersistenceAdapter implements IRolePersistencePorts {
    private final IRoleRepository repository;
    private final IRoleEntityMapper mapper;

    public RolePersistenceAdapter(IRoleRepository repository, IRoleEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleModel> getRoles() {
        return repository.findAll().stream().map(mapper::toRoleModel).toList();
    }

    @Override
    public RoleModel getRole(Long id) {
        return repository.findById(id).map(mapper::toRoleModel).orElse(null);
    }

    @Override
    public RoleModel createRole(RoleModel roleModel) {
        System.out.println("In role persistance "+roleModel.getRole());
        Role role = mapper.toRole(roleModel);
        Role savedRole = repository.save(role);
        return mapper.toRoleModel(savedRole);
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {
        repository.deleteById(id);
    }
}
