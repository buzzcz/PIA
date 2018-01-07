package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;
import cz.zcu.kiv.pia.kivbook.persistence.service.PostPersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	public Set<PostDto> getPostsForUser(UserDto user) {
		log.debug("Finding posts for {}.", user.getUsername());
		Set<PostDto> retVal = new TreeSet<>(Comparator.comparing(PostDto::getCreated));
		retVal.addAll(postPersistenceService.getAll(user));

		return retVal;
	}

	@Override
	public Set<PostDto> getPostsForUserAndFriends(UserDto user, List<UserDto> friends) {
		log.debug("Finding posts for {} and friends.", user.getUsername());
		Set<PostDto> retVal = new TreeSet<>(Comparator.comparing(PostDto::getCreated));
		retVal.addAll(postPersistenceService.getAllPublic());
		retVal.addAll(postPersistenceService.getAll(user));

		if (friends == null) {
			friends = friendService.getFriends(user);
		}

		for (UserDto friend : friends) {
			retVal.addAll(postPersistenceService.getAll(friend));
		}

		return retVal;
	}

	@Override
	public Set<PostDto> getPostsForUserAndFriends(UserDto user) {
		return getPostsForUserAndFriends(user, null);
	}

}
