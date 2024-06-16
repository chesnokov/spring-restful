package com.example.rest.controller;

import com.example.mapper.UserMapper;
import com.example.rest.model.RestUser;
import com.example.service.UserService;
import com.example.service.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;

	/**
	 * GET /user : получить список всех пользователей
	 *
	 * @return Пользователь успешно получен (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/user",
			produces = { "application/json" }
	)
	public ResponseEntity<List<RestUser>> getAllUsers() {
		List<UserEntity> users = userService.getAllUsers();
		List<RestUser> restUsers = users.stream()
				.map( userMapper::userToRestUser).toList();
		return new ResponseEntity<>(restUsers, HttpStatus.OK);
	}

	/**
	 * GET /user/{userId} : получить пользователя по идентификатору
	 *
	 * @param userId идентификатор пользователя (required)
	 * @return пользователь найден успешно (status code 200)
	 *         or пользователь не найден (status code 404)
	 */
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/user/{userId}",
			produces = { "application/json" }
	)
	public ResponseEntity<RestUser> getUserById(@PathVariable("userId") Long userId) {
		Optional<UserEntity> user = userService.getUserById(userId);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			RestUser restUser = userMapper.userToRestUser(user.get());
			return new ResponseEntity<>(restUser, HttpStatus.OK);
		}
	}

	/**
	 * POST /user : добавить пользователя
	 *
	 * @param restUser  (required)
	 * @return пользователь добавлен успешно (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/user",
			produces = { "application/json" },
			consumes = { "application/json" }
	)
	public ResponseEntity<RestUser> addUser(@RequestBody RestUser restUser) {
		UserEntity user = userMapper.restUserToUser(restUser);
		user = userService.addUser(user);
		RestUser resultUser = userMapper.userToRestUser(user);
		return new ResponseEntity<>(resultUser, HttpStatus.OK);
	}

	/**
	 * PUT /user/{userId} : модифицировать данные пользователя по id
	 *
	 * @param userId идентификатор пользователя (required)
	 * @param restUser  (required)
	 * @return пользователь модифицирован успешно (status code 200)
	 *         or пользователь не найден (status code 404)
	 */
	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/user/{userId}",
			produces = { "application/json" },
			consumes = { "application/json" }
	)
	public ResponseEntity<RestUser> updateUser(@PathVariable("userId") Long userId, @RequestBody RestUser restUser) {
		UserEntity user = userMapper.restUserToUser(restUser);
		Optional<UserEntity> updatedUser = userService.updateUser(userId, user);
		if(updatedUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			RestUser resultUser = userMapper.userToRestUser(updatedUser.get());
			return new ResponseEntity<>(resultUser, HttpStatus.OK);
		}
	}

	/**
	 * DELETE /user/{userId} : удалить пользователя по идентификатору
	 *
	 * @param userId идентификатор пользователя (required)
	 * @return пользователь удален успешно (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/user/{userId}"
	)
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
