package project.UserService.application.services;

import org.springframework.stereotype.Service;
import project.UserService.application.ports.inputs.IRoleServicePorts;
import project.UserService.application.ports.outputs.IRolePersistencePorts;
import project.UserService.domain.models.RoleModel;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleServicePorts {

    private final IRolePersistencePorts rolePersistencePort;

    public RoleServiceImpl(IRolePersistencePorts rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public List<RoleModel> getRoles() {
        return rolePersistencePort.getRoles();
    }

    @Override
    public RoleModel getRole(Long id) {
        return rolePersistencePort.getRole(id);
    }

    @Override
    public RoleModel createRole(RoleModel role) {
        System.out.println("In role service "+role.getRole());
        return rolePersistencePort.createRole(role);
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        return rolePersistencePort.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        rolePersistencePort.deleteRole(id);
    }
}
