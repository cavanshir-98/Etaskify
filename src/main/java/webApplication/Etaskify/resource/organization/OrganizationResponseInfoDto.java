package webApplication.Etaskify.resource.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webApplication.Etaskify.model.Organization;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponseInfoDto {

    private Long id;
    private String name;

    public static OrganizationResponseInfoDto getDto(Organization organization) {
        return OrganizationResponseInfoDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .build();
    }
}
