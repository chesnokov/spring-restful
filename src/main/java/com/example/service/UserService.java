package com.example.service;

import com.example.service.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
	List<UserEntity> getAllUsers();
	Optional<UserEntity> getUserById(Long id);
	UserEntity addUser(UserEntity user);
	Optional<UserEntity> updateUser(Long id, UserEntity user);
	void deleteUser(Long id);
}
