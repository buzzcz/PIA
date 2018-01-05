package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.MessageDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Message;
import cz.zcu.kiv.pia.kivbook.persistence.repository.MessageRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jaroslav Klaus
 */
public class MessagePersistenceServiceImpl implements MessagePersistenceService {

	@Autowired
	private MessageRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<MessageDto> getAll(Integer userId1) {
		List<Message> messages = repository.findAll();
		List<MessageDto> messageDtos = new LinkedList<>();
		for (Message m : messages) {
			messageDtos.add(mapper.map(m, MessageDto.class));
		}

		return messageDtos;
	}

	@Override
	public MessageDto save(MessageDto message) {
		return null;
	}

}
