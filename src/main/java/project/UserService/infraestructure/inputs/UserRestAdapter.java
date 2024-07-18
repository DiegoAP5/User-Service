package project.UserService.infraestructure.inputs;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.UserService.application.ports.inputs.IUserServicePorts;
import project.UserService.domain.models.UserModel;
import project.UserService.infraestructure.inputs.dtos.request.CreateUserRequest;
import project.UserService.infraestructure.inputs.dtos.request.UpdateUserRequest;
import project.UserService.infraestructure.inputs.dtos.response.BaseResponse;
import project.UserService.infraestructure.inputs.mapper.IUserModelMapper;
import project.UserService.infraestructure.outputs.mappers.IUserEntintyMapper;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserRestAdapter {
    private final IUserServicePorts servicePorts;
    private final IUserModelMapper userMapper;

    public UserRestAdapter(IUserServicePorts servicePorts, IUserModelMapper userMapper) {
        this.servicePorts = servicePorts;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> getUsers() {
        List<UserModel> users = servicePorts.list();

        BaseResponse response = BaseResponse.builder()
                .data(users)
                .message("Get all users")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

        return response.toResponseEntity();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BaseResponse> getUserByEmail(@PathVariable String email) {
        UserModel user = servicePorts.findByEmail(email);

        BaseResponse response = BaseResponse.builder()
                .data(user)
                .success(Boolean.TRUE)
                .message("User getted by email")
                .httpStatus(HttpStatus.OK)
                .build();

        return response.toResponseEntity();
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createUser(@Valid @RequestBody CreateUserRequest user) {
        UserModel createdUser = servicePorts.create(userMapper.toUserModel(user));

        BaseResponse response = BaseResponse.builder()
                .data(createdUser)
                .message("User created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();

        return response.toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id) {
        UserModel userModel = servicePorts.findById(id);

        BaseResponse response = BaseResponse.builder()
                .data(userModel)
                .message("User data")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

        return response.toResponseEntity();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest user) {
        UserModel updatedUser = servicePorts.update(id, user);

        BaseResponse response = BaseResponse.builder()
                .data(updatedUser)
                .message("User updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.ACCEPTED)
                .build();

        return response.toResponseEntity();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Long id) {
        servicePorts.delete(id);

        BaseResponse response = BaseResponse.builder()
                .message("User deleted")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

        return response.toResponseEntity();
    }
}
