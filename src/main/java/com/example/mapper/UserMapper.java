package com.example.mapper;

import com.example.persistence.model.UserData;
import com.example.rest.model.RestUser;
import com.example.service.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserData userToUserData(UserEntity user);
	RestUser userToRestUser(UserEntity user);
	UserEntity userDataToUser(UserData userData);
	UserEntity restUserToUser(RestUser restUser);
}
