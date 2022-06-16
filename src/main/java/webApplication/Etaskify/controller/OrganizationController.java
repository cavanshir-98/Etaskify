package webApplication.Etaskify.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webApplication.Etaskify.resource.organization.AddUserToOrgDto;
import webApplication.Etaskify.resource.organization.OrganizationCreateRequestDto;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;
import webApplication.Etaskify.service.OrganizationService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseInfoDto> add(@RequestBody @Valid OrganizationCreateRequestDto requestDto) {
        log.trace("Create OrganizationResponseInfoDto request {}", requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(requestDto));
    }


    @DeleteMapping("/organization/{organizationId}")
    public ResponseEntity<Void> deleteOrganizationUser(@PathVariable Long organizationId,
                                                       @RequestBody @Valid AddUserToOrgDto registerOrganizationUser) {
        log.trace("Delete OrganizationUser {},{}  request", organizationId, registerOrganizationUser);
        organizationService.removeUserFromOrganization(registerOrganizationUser, organizationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/organization/{organizationId}")
    public ResponseEntity<Void> addOrganizationUsers(@PathVariable Long organizationId,
                                                     @RequestBody @Valid AddUserToOrgDto registerOrganizationUser) {
        log.trace("Add GroupUser  {},{}  request", organizationId, registerOrganizationUser);
        organizationService.addUserToOrganization(registerOrganizationUser, organizationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
