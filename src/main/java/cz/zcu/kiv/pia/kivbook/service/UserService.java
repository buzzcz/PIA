package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;
import java.util.Set;

/**
 * Interface for operations with Users.
 *
 * @author Jaroslav Klaus
 */
public interface UserService {

	UserDto getUser(String username);

	List<UserDto> getUsersFromConversations(Set<ConversationDto> conversations, UserDto user);

	List<UserDto> searchUsers(String text);

	UserDto save(UserDto user);

}
