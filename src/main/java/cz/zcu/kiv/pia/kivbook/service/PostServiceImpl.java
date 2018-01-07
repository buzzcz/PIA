package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.PostPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of PostService.
 *
 * @author Jaroslav Klaus
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {

	@Autowired
	private PostPersistenceService postPersistenceService;

	@Autowired
	private FriendService friendService;

	@Override
	public List<PostDto> getPostsForUserAndFriends(UserDto user, List<UserDto> friends) {
		log.debug("Finding posts for {} and friends.", user.getUsername());
		List<PostDto> retVal = new LinkedList<>();
		retVal.addAll(postPersistenceService.getAllPublic());
		retVal.addAll(postPersistenceService.getAll(user));

		if (friends == null) {
			friends = friendService.getFriends(user);
		}

		for (UserDto friend : friends) {
			retVal.addAll(postPersistenceService.getAll(friend));
		}

		retVal.sort(Comparator.comparing(PostDto::getCreated));

		return retVal;
	}

	@Override
	public List<PostDto> getPostsForUser(UserDto user) {
		log.debug("Finding posts for {}.", user.getUsername());

		return getPostsForUserAndFriends(user, null);
	}

}
