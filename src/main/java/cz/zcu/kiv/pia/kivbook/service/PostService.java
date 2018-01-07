package cz.zcu.kiv.pia.kivbook.service;

import cz.zcu.kiv.pia.kivbook.dto.PostDto;
import cz.zcu.kiv.pia.kivbook.dto.UserDto;

import java.util.List;

/**
 * Interface for operations with posts.
 *
 * @author Jaroslav Klaus
 */
public interface PostService {

	List<PostDto> getPostsForUserAndFriends(UserDto user, List<UserDto> friends);

	List<PostDto> getPostsForUser(UserDto user);

}
