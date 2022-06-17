package webApplication.Etaskify.service;

import org.springframework.data.domain.Page;
import webApplication.Etaskify.resource.organization.AddUserToOrgDto;
import webApplication.Etaskify.resource.organization.OrganizationCreateRequestDto;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;
import webApplication.Etaskify.resource.organization.OrganizationSearchRequest;

public interface OrganizationService {

    OrganizationResponseInfoDto create(OrganizationCreateRequestDto requestDto);

    void addUserToOrganization(AddUserToOrgDto registerOrgUser, Long id);

    void removeUserFromOrganization(AddUserToOrgDto registerOrgUser, Long id);

    Page<OrganizationResponseInfoDto> list(OrganizationSearchRequest searchRequest);

}
