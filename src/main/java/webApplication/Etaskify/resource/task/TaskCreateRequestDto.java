package webApplication.Etaskify.resource.task;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import webApplication.Etaskify.enums.TaskStatus;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.resource.user.UserDto;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Valid
    private TaskStatus status;

    @Column(name="deadline", nullable = false)
    private LocalDate deadline;

    private Long organizationId;
}
