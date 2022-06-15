package webApplication.Etaskify.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import webApplication.Etaskify.exception.organization.OrganizationAlreadyExistException;
import webApplication.Etaskify.exception.organization.OrganizationNotFoundException;
import webApplication.Etaskify.mapper.OrganizationMapper;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.repository.OrganizationRepository;
import webApplication.Etaskify.repository.UserRepository;
import webApplication.Etaskify.resource.organization.AddUserToOrgDto;
import webApplication.Etaskify.resource.organization.OrganizationCreateRequestDto;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;
import webApplication.Etaskify.service.OrganizationService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserRepository userRepository;

    @Override
    public OrganizationResponseInfoDto create(OrganizationCreateRequestDto requestDto) {
        organizationRepository.findByName(requestDto.getName())
                .ifPresent(organization -> {
                    throw new RuntimeException(requestDto.getName());
                });

        Organization organization = organizationMapper.requestToOrganization(requestDto);
        return organizationMapper.organizationToDto(organizationRepository.save(organization));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void addUserToOrganization(AddUserToOrgDto registerOrgUser, Long id) {
        Organization organization = organizationFindById(id);
        User user = userFindById(registerOrgUser.getUserId());
        checkUserIsAlreadyInTheOrg(organization, registerOrgUser.getUserId());
        List<User> users = organization.getUser();
        users.add(user);
        organization.setUser(users);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void removeUserFromOrganization(AddUserToOrgDto registerOrgUser, Long id) {
        Organization organization = organizationFindById(id);
        User user = userFindById(registerOrgUser.getUserId());
        List<User> users = organization.getUser();
        users.remove(user);
        organization.setUser(users);
    }


    private Organization organizationFindById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));
    }

    private User userFindById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    private void checkUserIsAlreadyInTheOrg(Organization organization, Long userId) {
        Set<User> test = organization.getUser().stream()
                .filter(u -> u.getId() == userId)
                .collect(Collectors.toSet());
        if (!test.isEmpty()) {
            throw new OrganizationAlreadyExistException(userId);
        }
    }
}
