package project.UserService.infraestructure.inputs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.UserService.application.ports.inputs.IRoleServicePorts;
import project.UserService.domain.models.RoleModel;
import project.UserService.infraestructure.inputs.dtos.request.CreateRoleRequest;
import project.UserService.infraestructure.inputs.dtos.response.BaseResponse;
import project.UserService.infraestructure.inputs.mapper.IRoleModelMapper;
import project.UserService.infraestructure.outputs.mappers.IRoleEntityMapper;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleRestAdapter {
    private final IRoleServicePorts servicePorts;
    private final IRoleModelMapper mapper;

    public RoleRestAdapter(IRoleServicePorts servicePorts, IRoleModelMapper mapper) {
        this.servicePorts = servicePorts;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getRoles() {
        List<RoleModel> roles = servicePorts.getRoles();

        BaseResponse response = BaseResponse.builder()
                .data(roles)
                .message("All roles")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

        return response.toResponseEntity();
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createRole(@RequestBody CreateRoleRequest role) {
        RoleModel roleModelReq = mapper.toRoleModel(role);
        System.out.println("hola" + roleModelReq.getRole());

        RoleModel resp = servicePorts.createRole(roleModelReq);


        BaseResponse response = BaseResponse.builder()
                .data(resp)
                .message("Role created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();

        return response.toResponseEntity();
    }
}
