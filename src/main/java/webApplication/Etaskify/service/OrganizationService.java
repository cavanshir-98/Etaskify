package webApplication.Etaskify.service;

import webApplication.Etaskify.resource.organization.AddUserToOrgDto;
import webApplication.Etaskify.resource.organization.OrganizationCreateRequestDto;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;

public interface OrganizationService {

    OrganizationResponseInfoDto create(OrganizationCreateRequestDto requestDto);

    void addUserToOrganization(AddUserToOrgDto registerOrgUser, Long id);

    void removeUserFromOrganization(AddUserToOrgDto registerOrgUser, Long id);

}
