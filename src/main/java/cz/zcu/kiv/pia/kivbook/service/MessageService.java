package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.MessageDto;

import java.util.Set;

/**
 * Interface for operations with Messages.
 *
 * @author Jaroslav Klaus
 */
public interface MessageService {

	Set<MessageDto> getAllForConversation(ConversationDto conversationDto);

	MessageDto save(MessageDto message);
}
