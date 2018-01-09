package cz.zcu.kiv.pia.kivbook.persistence.service;

import cz.zcu.kiv.pia.kivbook.dto.FriendDto;
import cz.zcu.kiv.pia.kivbook.persistence.entity.Friend;
import cz.zcu.kiv.pia.kivbook.persistence.repository.FriendRepository;
import cz.zcu.kiv.pia.kivbook.service.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jaroslav Klaus
 */
@Service
public class FriendPersistenceServiceImpl implements FriendPersistenceService {

	@Autowired
	private FriendRepository repository;

	@Autowired
	private DtoConvertor mapper;

	@Override
	public List<FriendDto> getAllAckTrue(Integer userId) {
		List<Friend> friends = repository.findByUserId1AndAckTrue(userId);
		List<FriendDto> friendDtos = mapper.map(friends, FriendDto.class);
		friends = repository.findByUserId2AndAckTrue(userId);
		friendDtos.addAll(mapper.map(friends, FriendDto.class));

		return friendDtos;
	}

	@Override
	public FriendDto get(Integer userId1, Integer userId2) {
		Friend friend = repository.findByUserId1AndUserId2(userId1, userId2);
		if (friend != null) {
			return mapper.map(friend, FriendDto.class);
		}

		return null;
	}

	@Override
	public FriendDto save(FriendDto friend) {
		Friend entity = mapper.map(friend, Friend.class);
		entity = repository.save(entity);

		return mapper.map(entity, FriendDto.class);
	}

}
