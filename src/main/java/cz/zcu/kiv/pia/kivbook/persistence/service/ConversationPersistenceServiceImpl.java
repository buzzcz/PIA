package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Conversation;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.ConversationRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
@Service
public class ConversationPersistenceServiceImpl implements ConversationPersistenceService {

	@Autowired
	private ConversationRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<ConversationDto> getAll(UserDto user) {
		User entity = mapper.map(user, User.class);
		List<Conversation> conversations = repository.findByUser1OrderByCreatedDesc(entity);
		conversations.addAll(repository.findByUser2OrderByCreatedDesc(entity));
		List<ConversationDto> conversationDtos = new LinkedList<>();
		for (Conversation c : conversations) {
			conversationDtos.add(mapper.map(c, ConversationDto.class));
		}

		return conversationDtos;
	}

	@Override
	public ConversationDto save(ConversationDto conversation) {
		Conversation entity = mapper.map(conversation, Conversation.class);
		entity = repository.save(entity);

		return mapper.map(entity, ConversationDto.class);
	}

}
