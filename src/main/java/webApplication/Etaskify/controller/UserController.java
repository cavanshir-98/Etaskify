package webApplication.Etaskify.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.Etaskify.resource.user.RegisterUserDto;
import webApplication.Etaskify.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated RegisterUserDto dto) {
        userService.create(dto);
        return ResponseEntity.ok().build();
    }

}
