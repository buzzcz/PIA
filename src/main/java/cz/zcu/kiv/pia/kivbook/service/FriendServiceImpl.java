package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.FriendDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.FriendPersistenceService;
import cz.zcu.kiv.pia.kivbook.persistence.service.UserPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of FriendService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendPersistenceService friendPersistenceService;

	@Autowired
	private UserPersistenceService userPersistenceService;

	@Override
	public List<UserDto> getFriends(UserDto userDto) {
		log.debug("Finding friends for {}.", userDto.getUsername());
		int id = userDto.getId();
		List<FriendDto> friends = friendPersistenceService.getAll(id);
		List<UserDto> retVal = new LinkedList<>();

		for (FriendDto f : friends) {
			int friendId = f.getUserId1();
			if (friendId == id) {
				friendId = f.getUserId2();
			}

			retVal.add(userPersistenceService.get(friendId));
		}

		return retVal;
	}

	@Override
	public FriendDto areFriends(UserDto user1, UserDto user2) {
		log.debug("Checking if {} and {} are friends.", user1, user2);
		Integer id1 = user1.getId();
		Integer id2 = user2.getId();
		FriendDto retVal = friendPersistenceService.get(id1, id2);
		if (retVal == null) {
			retVal = friendPersistenceService.get(id2, id1);
		}

		return retVal;
	}

	@Override
	public FriendDto save(FriendDto friendship) {
		log.debug("Saving friendship {}.", friendship);

		return friendPersistenceService.save(friendship);
	}

}
