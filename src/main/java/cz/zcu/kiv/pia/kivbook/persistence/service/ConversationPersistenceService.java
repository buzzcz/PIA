package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.Set;

/**
 * Service for working with Conversation persistence.
 *
 * @author Jaroslav Klaus
 */
public interface ConversationPersistenceService {

	/**
	 * Retrieves all conversations for specified user.
	 *
	 * @param user User whose conversations to get.
	 * @return All conversations for specified user.
	 */
	Set<ConversationDto> getAll(UserDto user);

	/**
	 * Saves new conversation.
	 *
	 * @param conversation New conversation to save.
	 * @return Saved conversation.
	 */
	ConversationDto save(ConversationDto conversation);

}
