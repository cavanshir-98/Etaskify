package webApplication.Etaskify.mapper;

import org.mapstruct.Mapper;
import webApplication.Etaskify.model.User;
import webApplication.Etaskify.resource.user.UserDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserMapper {

    UserDto entityToDto(User user);


}
