package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.MessageDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Message;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.MessageRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jaroslav Klaus
 */
@Service
public class MessagePersistenceServiceImpl implements MessagePersistenceService {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public Set<MessageDto> getForConversation(Integer conversationId, UserDto user1, UserDto user2) {
		User entity = mapper.map(user1, User.class);
		Set<Message> messages = new TreeSet<>(Comparator.comparing(Message::getCreated));
		messages.addAll(repository.findByConversationIdAndOwnerOrderByCreated(conversationId, entity));
		entity = mapper.map(user2, User.class);
		messages.addAll(repository.findByConversationIdAndOwnerOrderByCreated(conversationId, entity));

		return mapper.map(messages, MessageDto.class);
	}

	@Override
	public MessageDto save(MessageDto message) {
		Message entity = mapper.map(message, Message.class);
		entity = repository.save(entity);

		return mapper.map(entity, MessageDto.class);
	}

}
