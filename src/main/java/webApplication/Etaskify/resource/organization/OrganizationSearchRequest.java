package webApplication.Etaskify.resource.organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSearchRequest extends PageParams{

    private Long id;
    private String name;
}
