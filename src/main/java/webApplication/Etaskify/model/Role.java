package webApplication.Etaskify.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import webApplication.Etaskify.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Data
@Builder
@FieldNameConstants
@ToString
@Table(name = "role")
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;
}
