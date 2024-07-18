package project.UserService.application.ports.inputs;

import project.UserService.domain.models.RoleModel;

import java.util.List;

public interface IRoleServicePorts {
    List<RoleModel> getRoles();
    RoleModel getRole(Long id);
    RoleModel createRole(RoleModel role);
    RoleModel updateRole(RoleModel role);
    void deleteRole(Long id);
}
