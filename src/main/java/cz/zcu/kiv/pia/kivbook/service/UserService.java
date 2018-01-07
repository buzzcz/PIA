package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;

/**
 * Interface for operations with Users.
 *
 * @author Jaroslav Klaus
 */
public interface UserService {

	UserDto getUser(String username);

	UserDto save(UserDto user);

}
