package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Interface for operations with Users.
 *
 * @author Jaroslav Klaus
 */
public interface UserService {

	UserDto getUser(String username);

	List<UserDto> getUsersFromConversations(List<ConversationDto> conversations, UserDto user);

	UserDto save(UserDto user);

}
