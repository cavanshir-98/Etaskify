package webApplication.Etaskify.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import webApplication.Etaskify.enums.TaskStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Organization organization;

    private LocalDate deadline;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_users",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> assignee;
}
