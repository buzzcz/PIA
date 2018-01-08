package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.ConversationPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ConversationService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private ConversationPersistenceService conversationPersistenceService;

	@Override
	public List<ConversationDto> getAllForUser(UserDto userDto) {
		log.debug("Getting conversations for user {}.", userDto);

		return conversationPersistenceService.getAll(userDto);
	}

}
