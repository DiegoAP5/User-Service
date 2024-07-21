package project.UserService.infraestructure.inputs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.UserService.application.ports.inputs.IUserServicePorts;
import project.UserService.infraestructure.inputs.dtos.request.AuthenticationRequest;
import project.UserService.infraestructure.inputs.dtos.response.BaseResponse;
import project.UserService.infraestructure.outputs.entities.User;
import project.UserService.infraestructure.outputs.mappers.IUserEntintyMapper;
import project.UserService.infraestructure.outputs.repositories.IUserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthRest {

    @Autowired
    private IUserServicePorts servicePorts;

    @Autowired
    private IUserEntintyMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private IUserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody AuthenticationRequest authRequest) {
        String email = authRequest.getEmail();
        String password = authRequest.getPassword();
        User user = repository.findByEmail(email);

        if (user!=null && passwordEncoder.matches(password, user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(email)
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            BaseResponse response1 = BaseResponse.builder()
                    .data(response)
                    .success(Boolean.TRUE)
                    .message("Authentication Successful")
                    .httpStatus(HttpStatus.ACCEPTED)
                    .build();
            return response1.toResponseEntity();
        } else {
            BaseResponse response = BaseResponse.builder()
                    .data(null)
                    .success(Boolean.FALSE)
                    .message("Invalid data")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
            return response.toResponseEntity();
        }
    }
}
