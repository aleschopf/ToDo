package tech.lastbox.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.lastbox.config.enviroment.JwtProperties;
import tech.lastbox.dtos.ErrorResponse;
import tech.lastbox.dtos.auth.LoginRequest;
import tech.lastbox.dtos.auth.LoginResponse;
import tech.lastbox.dtos.auth.RegisterRequest;
import tech.lastbox.dtos.auth.RegisterResponse;
import tech.lastbox.entities.User;
import tech.lastbox.exceptions.user.AlreadyExistsException;
import tech.lastbox.exceptions.user.InvalidDataException;
import tech.lastbox.jwt.JwtService;
import tech.lastbox.jwt.Token;
import tech.lastbox.services.UserService;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    public AuthController(UserService userService, JwtService jwtService, JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean authentication = userService.authenticateUser(loginRequest.username(), loginRequest.password());
        if (authentication) {
            Token token = jwtService.generateToken(loginRequest.username(), jwtProperties.getIssuer());
            return ResponseEntity.ok(new LoginResponse(token.token(), loginRequest.username()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.registerUser(registerRequest.username(), registerRequest.password());
            Token token = jwtService.generateToken(registerRequest.username(), jwtProperties.getIssuer());
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(user, token.token()));
        } catch (AlreadyExistsException alreadyExistsException) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(alreadyExistsException, HttpStatus.CONFLICT));
        } catch (InvalidDataException invalidDataException) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorResponse(invalidDataException, HttpStatus.NOT_ACCEPTABLE));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
