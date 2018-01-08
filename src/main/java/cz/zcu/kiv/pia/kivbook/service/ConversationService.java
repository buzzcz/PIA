package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Interface for operations with Conversations.
 *
 * @author Jaroslav Klaus
 */
public interface ConversationService {

	List<ConversationDto> getAllForUser(UserDto userDto);

}
