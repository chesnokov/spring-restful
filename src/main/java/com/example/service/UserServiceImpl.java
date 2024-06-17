package com.example.service;

import com.example.mapper.UserMapper;
import com.example.persistence.model.UserData;
import com.example.persistence.repository.TaskRepository;
import com.example.persistence.repository.UserRepository;
import com.example.service.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final TaskRepository taskRepository;
	private final UserMapper userMapper;


	@Transactional(readOnly = true)
	@Override
	public List<UserEntity> getAllUsers() {
		List<UserData> userData = userRepository.findAll();
		return userData.stream()
				.map( userMapper::userDataToUser).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<UserEntity> getUserById(Long id) {
		Optional<UserData> userData = userRepository.findById(id);
		return userData.map(userMapper::userDataToUser);
	}

	@Transactional
	@Override
	public UserEntity addUser(UserEntity user) {
		UserData userData = userMapper.userToUserData(user);
		userData = userRepository.save(userData);
		userData.getTasks().forEach(taskRepository::save);
		userData = userRepository.save(userData);
		return userMapper.userDataToUser(userData);
	}

	@Transactional
	@Override
	public Optional<UserEntity> updateUser(Long id, UserEntity user) {
		Optional<UserData> userData = userRepository.findById(id);
		if(userData.isEmpty()) {
			return Optional.empty();
		} else {
			UserData updatedUser = userMapper.userToUserData(user);
			updatedUser.getTasks().forEach(taskRepository::save);
			updatedUser = userRepository.save(updatedUser);
			UserEntity resultUser = userMapper.userDataToUser(updatedUser);
			return Optional.ofNullable(resultUser);
		}
	}

	@Transactional
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
