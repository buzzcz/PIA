package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Conversation;
import cz.zcu.kiv.pia.kivbook.persistence.repository.ConversationRepository;
import cz.zcu.kiv.pia.kivbook.service.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
public class ConversationPersistenceServiceImpl implements ConversationPersistenceService {

	@Autowired
	private ConversationRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<ConversationDto> getAll(Integer userId) {
		List<Conversation> conversations = repository.findByUserId1(userId);
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
