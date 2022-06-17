package webApplication.Etaskify.resource.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import webApplication.Etaskify.enums.TaskStatus;
import webApplication.Etaskify.resource.organization.OrganizationResponseInfoDto;
import webApplication.Etaskify.resource.user.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate deadline;
    private OrganizationResponseInfoDto organization;
}
