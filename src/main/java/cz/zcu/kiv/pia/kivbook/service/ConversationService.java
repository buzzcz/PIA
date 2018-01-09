package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.Set;

/**
 * Interface for operations with Conversations.
 *
 * @author Jaroslav Klaus
 */
public interface ConversationService {

	ConversationDto get(Integer id);

	Set<ConversationDto> getAllForUser(UserDto userDto);

	ConversationDto save(ConversationDto conversation);

}
