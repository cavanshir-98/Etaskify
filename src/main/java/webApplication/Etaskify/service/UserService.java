package webApplication.Etaskify.service;


import webApplication.Etaskify.resource.user.RegisterUserDto;
import webApplication.Etaskify.resource.user.UserDto;

public interface UserService {

    void create(RegisterUserDto dto);
}
