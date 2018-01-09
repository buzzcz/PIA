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
	public PostDto get(Integer postId) {
		log.debug("Finding post {}.", postId);

		return postPersistenceService.get(postId);
	}

	@Override
	public Set<PostDto> getPostsForUser(UserDto user) {
		log.debug("Finding posts for {}.", user.getUsername());
		Set<PostDto> retVal = new TreeSet<>(Comparator.comparing(PostDto::getCreated).reversed());
		retVal.addAll(postPersistenceService.getAll(user));

		return retVal;
	}

	@Override
	public Set<PostDto> getPostsForUserAndFriends(UserDto user, List<UserDto> friends, int pageNumber, int pageSize) {
		log.debug("Finding posts for {} and friends.", user.getUsername());
		TreeSet<PostDto> posts = new TreeSet<>(Comparator.comparing(PostDto::getCreated).reversed());
		posts.addAll(postPersistenceService.getAllPublic());
		posts.addAll(postPersistenceService.getAll(user));

		if (friends == null) {
			friends = friendService.getFriends(user);
		}

		for (UserDto friend : friends) {
			posts.addAll(postPersistenceService.getAll(friend));
		}

		Set<PostDto> retVal = new TreeSet<>(Comparator.comparing(PostDto::getCreated).reversed());
		for (int i = 0; i < pageNumber; i++) {
			for (int j = 0; j < pageSize && !posts.isEmpty(); j++) {
				posts.pollFirst();
			}
		}
		for (int i = 0; i < pageSize && !posts.isEmpty(); i++) {
			retVal.add(posts.pollFirst());
		}

		return retVal;
	}

	@Override
	public PostDto save(PostDto post) {
		log.debug("Saving post {}.", post);

		return postPersistenceService.save(post);
	}

}
