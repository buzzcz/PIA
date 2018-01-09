package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.MessageDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.Set;

/**
 * Service for working with Message persistence.
 *
 * @author Jaroslav Klaus
 */
public interface MessagePersistenceService {

	Set<MessageDto> getForConversation(Integer conversationId, UserDto user1, UserDto user2);

	/**
	 * Saves new message.
	 *
	 * @param message New message to save.
	 * @return Saved message.
	 */
	MessageDto save(MessageDto message);

}
