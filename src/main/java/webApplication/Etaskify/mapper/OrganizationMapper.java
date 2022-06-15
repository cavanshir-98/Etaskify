package webApplication.Etaskify.mapper;

import org.mapstruct.Mapper;
import webApplication.Etaskify.model.Organization;
import webApplication.Etaskify.resource.organization.OrganizationCreateRequestDto;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface OrganizationMapper {

    Organization requestToOrganization(OrganizationCreateRequestDto organizationRequestDto);

    OrganizationResponseInfoDto organizationToDto(Organization organization);

}
