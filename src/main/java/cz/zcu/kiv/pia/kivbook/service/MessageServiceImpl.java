package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.MessageDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.MessagePersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of MessageService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagePersistenceService messagePersistenceService;

	@Override
	public Set<MessageDto> getAllForConversation(ConversationDto conversation) {
		log.debug("Getting messages for conversation {}.", conversation);
		Integer conversationId = conversation.getId();
		Set<MessageDto> retVal = new TreeSet<>(Comparator.comparing(MessageDto::getCreated));
		retVal.addAll(messagePersistenceService.getForConversation(conversationId, conversation.getUser1(),
				conversation.getUser2()));

		return retVal;
	}

	@Override
	public MessageDto save(MessageDto message) {
		log.debug("Saving message {}.", message);

		return messagePersistenceService.save(message);
	}

}
