package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.ConversationDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Conversation;
import cz.zcu.kiv.pia.kivbook.persistence.entity.User;
import cz.zcu.kiv.pia.kivbook.persistence.repository.ConversationRepository;
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
public class ConversationPersistenceServiceImpl implements ConversationPersistenceService {

	@Autowired
	private ConversationRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public ConversationDto get(Integer id) {
		Conversation conversation = repository.findOne(id);
		if (conversation != null) {
			return mapper.map(conversation, ConversationDto.class);
		}

		return null;
	}

	@Override
	public Set<ConversationDto> getAll(UserDto user) {
		User entity = mapper.map(user, User.class);
		Set<Conversation> conversations = new TreeSet<>(Comparator.comparing(Conversation::getCreated).reversed());
		conversations.addAll(repository.findByUser1OrderByCreatedDesc(entity));
		conversations.addAll(repository.findByUser2OrderByCreatedDesc(entity));

		return mapper.map(conversations, ConversationDto.class);
	}

	@Override
	public ConversationDto save(ConversationDto conversation) {
		Conversation entity = mapper.map(conversation, Conversation.class);
		entity = repository.save(entity);

		return mapper.map(entity, ConversationDto.class);
	}

}
