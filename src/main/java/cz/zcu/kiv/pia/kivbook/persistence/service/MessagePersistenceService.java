package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.MessageDto;

import java.util.List;

/**
 * Service for working with Message persistence.
 *
 * @author Jaroslav Klaus
 */
public interface MessagePersistenceService {

	/**
	 * @param userId1
	 * @return All messages.
	 */
	List<MessageDto> getAll(Integer userId1);

	/**
	 * Saves new message.
	 *
	 * @param message New message to save.
	 * @return Saved message.
	 */
	MessageDto save(MessageDto message);

}
