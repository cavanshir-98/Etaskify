package webApplication.Etaskify.resource.organization;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode()
@FieldNameConstants
@SuperBuilder
@ToString
public class PageParams {

    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private static final String DEFAULT_DIR = "ASC";

    @PositiveOrZero
    private int page;

    @Positive
    private int size = DEFAULT_PAGE_SIZE;

    private String dir = DEFAULT_DIR;

    private String order;
}
