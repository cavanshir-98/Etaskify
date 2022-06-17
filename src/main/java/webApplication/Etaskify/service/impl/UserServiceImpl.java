package webApplication.Etaskify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webApplication.Etaskify.enums.RoleEnum;
import webApplication.Etaskify.exception.EmailAlreadyExistException;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.model.Role;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.repository.OrganizationRepository;
import webApplication.Etaskify.repository.RoleRepository;
import webApplication.Etaskify.repository.UserRepository;
import webApplication.Etaskify.resource.user.RegisterUserDto;
import webApplication.Etaskify.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(RegisterUserDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistException();
                });
        User user = createUserEntityObject(dto);
        userRepository.save(user);
    }

    private User createUserEntityObject(RegisterUserDto registerUserDto) {

        User user = new User();
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        Set<Role> roleEntities = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Not found Authority"));
        roleEntities.add(userRole);
        user.setRoles(roleEntities);
        user.setName(registerUserDto.getName());
        user.setEmail(registerUserDto.getEmail());
        user.setPhoneNumber(registerUserDto.getPhoneNumber());
        return user;
    }

}
