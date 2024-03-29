package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Service for working with User persistence.
 *
 * @author Jaroslav Klaus
 */
public interface UserPersistenceService {

	/**
	 * @return All users.
	 */
	List<UserDto> getAll();

	/**
	 * Retrieves user with specified id.
	 *
	 * @param id Id of the user to retrieve.
	 * @return User with specified id or null if none exists.
	 */
	UserDto get(Integer id);

	/**
	 * Retrieves user with specified username.
	 *
	 * @param username Username of the user to retrieve.
	 * @return User with specified username or null if none exists.
	 */
	UserDto get(String username);

	List<UserDto> searchUsers(String text);

	/**
	 * Saves new user.
	 *
	 * @param user New user to save.
	 * @return Saved user.
	 */
	UserDto save(UserDto user);

}
