package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserPersistenceService userPersistenceService;

	@Override
	public UserDto save(UserDto user) {
		log.debug("Saving user {}.", user);

		return userPersistenceService.save(user);
	}

	@Override
	public UserDto getUser(String username) {
		log.debug("Finding user {}.", username);

		return userPersistenceService.get(username);
	}

}
