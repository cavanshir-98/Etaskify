package webApplication.Etaskify.service;

import org.springframework.http.ResponseEntity;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.resource.user.LoginCreateRequestDto;
import webApplication.Etaskify.resource.user.RegisterRequest;

public interface AuthService {
    User register(RegisterRequest registerRequest);

    ResponseEntity<?> login(LoginCreateRequestDto loginRequest);

}
