package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	public List<UserDto> getUsersFromConversations(Set<ConversationDto> conversations, UserDto user) {
		log.debug("Getting users from conversations {}.", conversations);
		List<UserDto> retVal = new LinkedList<>();
		for (ConversationDto c : conversations) {
			UserDto u = c.getUser1();
			if (!u.equals(user)) {
				retVal.add(u);
			} else {
				retVal.add(c.getUser2());
			}
		}

		return retVal;
	}

	@Override
	public List<UserDto> searchUsers(String text) {
		log.debug("Searching user with text {}.", text);

		return userPersistenceService.searchUsers(text);
	}

	@Override
	public UserDto getUser(String username) {
		log.debug("Finding user {}.", username);

		return userPersistenceService.get(username);
	}

}
