package webApplication.Etaskify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import webApplication.Etaskify.enums.RoleEnum;
import webApplication.Etaskify.exception.EmailAlreadyExistException;
import webApplication.Etaskify.exception.EmailOrPasswordInvalid;
import webApplication.Etaskify.exception.RoleNotFoundException;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.model.Role;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.repository.OrganizationRepository;
import webApplication.Etaskify.repository.RoleRepository;
import webApplication.Etaskify.repository.UserRepository;
import webApplication.Etaskify.resource.token.TokenResponse;
import webApplication.Etaskify.resource.user.LoginCreateRequestDto;
import webApplication.Etaskify.resource.user.RegisterRequest;
import webApplication.Etaskify.security.JwtUtils;
import webApplication.Etaskify.security.userDetails.UserDetailsImpl;
import webApplication.Etaskify.service.AuthService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final OrganizationRepository organizationRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, OrganizationRepository organizationRep, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public User register(RegisterRequest registerRequest) {

        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));
        Set<Role> roles = new HashSet<>();

        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) {
            roleRepository.save(Role.builder().name(RoleEnum.ADMIN).build());
            roleRepository.save(Role.builder().name(RoleEnum.USER).build());
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistException();
        }
        if (registerRequest.getAuthority().contains("ADMIN")) {
            Role admin = roleRepository.findByName(RoleEnum.ADMIN)
                    .orElseThrow(RoleNotFoundException::new);
            roles.add(admin);
        }
        if (registerRequest.getAuthority().contains("USER")) {
            Role userRole = roleRepository.findByName(RoleEnum.USER)
                    .orElseThrow(RoleNotFoundException::new);
            roles.add(userRole);
        }

        Organization organization = new Organization(registerRequest.getOrganizationName(), user);
        organizationRepository.save(organization);
        user.setRoles(roles);
        user.setOrganization(organization);
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        userRepository.save(user);
        return user;
    }

    @Override
    public ResponseEntity<?> login(LoginCreateRequestDto loginRequest) {

        User byEmail = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(EmailOrPasswordInvalid::new);
        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), byEmail.getPassword());
        if (!matches) {
            throw new EmailOrPasswordInvalid();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new TokenResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                authorities));

    }


}

